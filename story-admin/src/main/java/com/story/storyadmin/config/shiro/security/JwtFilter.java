package com.story.storyadmin.config.shiro.security;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.story.storyadmin.config.shiro.LoginUser;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.common.ISyncCacheService;
import com.story.storyadmin.utils.JedisUtils;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * jwt过滤器
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** jwt设置参数 构造器注入bean对象*/
    JwtProperties jwtProperties;
    ISyncCacheService syncCacheService;
    JedisUtils jedisUtils;

    public JwtFilter(JwtProperties jwtProperties, ISyncCacheService syncCacheService, JedisUtils jedisUtils){
        this.jwtProperties=jwtProperties;
        this.syncCacheService=syncCacheService;
        this.jedisUtils = jedisUtils;
    }

    /**
     * 检测Header里Authorization字段
     * 判断是否登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(SecurityConsts.REQUEST_AUTH_HEADER);
        return authorization != null;
    }

    /**
     * 登录验证
     * 当前请求executeLogin验证成功后，调用refreshTokenIfNeed检查请求中携带的Token是否已经生效2小时，如果是就重新颁发
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        logger.info("调用executeLogin验证登录");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(SecurityConsts.REQUEST_AUTH_HEADER);

        //将token存入到JwtToken对象中，构成一个新的JwtToken对象  TODO JwtToken对象的作用是什么？
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);

        //TODO 绑定上下文 获取账号
        String account = JwtUtil.getClaim(authorization, SecurityConsts.ACCOUNT);
        // 将LoginUser 设置为线程内部属性，方便其他线程获取
        // UserContext userContext= new UserContext(new LoginUser(account));
           new UserContext(new LoginUser(account));
           // 当我们在业务中需要访问上下文用户时，可以这样获取：UserContext.getCurrentUser().getAccount()

        //检查是否需要更换token，需要则重新颁发
        this.refreshTokenIfNeed(account, authorization, response);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 加入Token验证通过后定时刷新Token的逻辑
     * 解决Token过期后刷新的逻辑，那么我们需要增加Token过期前更新的逻辑,
     * 将原来设计的Token到期后刷新，重新修改为Token在有效期内刷新，使得Token一旦到期，则直接跳转到登录页，保证了同一个用户，并发的请求只会更换一次令牌
     * 检查是否需要刷新token,若需要则校验时间戳，刷新Token，并更新时间戳
     * @param account
     * @param authorization
     * @param response
     * @return
     */
    private boolean refreshTokenIfNeed(String account, String authorization, ServletResponse response) {
        //系统当前时间戳，毫秒
        Long currentTimeMillis = System.currentTimeMillis();
        //检查刷新规则
        if (this.refreshCheck(authorization, currentTimeMillis)) {
            String lockName = SecurityConsts.PREFIX_SHIRO_REFRESH_CHECK + account;
            //获取锁
            boolean b = syncCacheService.getLock(lockName, Constants.ExpireTime.ONE_MINUTE);
            if (b) {
                //b为true获取到锁
                // refreshTokenKey是登录的时候设置的缓存时间戳
                // TODO 这个是记住我缓存key
                String refreshTokenKey= SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account;
                // TODO 这个是没有记住我缓存key
                String refreshTokenKeyNoRemeberMe = SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account + "rememberMe";
                Boolean rememberMe = null;
                //判断redis是否缓存了数据
                if(jedisUtils.exists(refreshTokenKey)){
                    // 记住我
                    rememberMe = true; 
                    //获取缓存中的时间戳
                    String tokenTimeStamp = jedisUtils.get(refreshTokenKey);
                    //获取token中时间戳
                    String tokenMillis= JwtUtil.getClaim(authorization,SecurityConsts.CURRENT_TIME_MILLIS);
                    //检查redis中的时间戳与token的时间戳是否一致
                    if(!tokenMillis.equals(tokenTimeStamp)){
                        throw new TokenExpiredException(String.format("账户%s的令牌无效", account));
                    }
                } else if(jedisUtils.exists(refreshTokenKeyNoRemeberMe)){
                    rememberMe = false;
                    //获取缓存中的时间戳
                    String tokenTimeStamp = jedisUtils.get(refreshTokenKeyNoRemeberMe);
                    //获取token中时间戳
                    String tokenMillis= JwtUtil.getClaim(authorization,SecurityConsts.CURRENT_TIME_MILLIS);
                    //检查redis中的时间戳与token的时间戳是否一致
                    if(!tokenMillis.equals(tokenTimeStamp)){
                        throw new TokenExpiredException(String.format("账户%s的令牌无效", account));
                    }
                }

                //时间戳一致，则颁发新的令牌
                logger.info(String.format("为账户%s颁发新的令牌", account));
                //系统当前时间
                String strCurrentTimeMillis = String.valueOf(currentTimeMillis);
                String newToken = null;
                if(rememberMe){
                    //生成新的签名token,n分钟后过期
                   newToken = JwtUtil.sign(account,strCurrentTimeMillis,true);
                    //更新缓存中的token时间戳，TODO 主要更新的是currentTimeMillis系统当前时间戳数据，过期时间在程序里是写死的
                    //将数据存入缓存（并设置失效时间为24小时）
                    jedisUtils.saveString(refreshTokenKey, strCurrentTimeMillis, jwtProperties.getTokenExpireTime()*60);
                }
                //生成新的签名token,n分钟后过期
                  newToken = JwtUtil.sign(account,strCurrentTimeMillis,false);
                //更新缓存中的token时间戳，
                //将数据存入缓存（并设置失效时间为24分钟）
                jedisUtils.saveString(refreshTokenKeyNoRemeberMe, strCurrentTimeMillis, jwtProperties.getTokenExpireTime());

                //设置httpServletResponse响应头将新的token返回给前端
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader(SecurityConsts.REQUEST_AUTH_HEADER, newToken);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", SecurityConsts.REQUEST_AUTH_HEADER);
            }
            //释放线程锁
            syncCacheService.releaseLock(lockName);
        }
        return true;
    }

    /**
     * 检查是否需要更新Token
     * 这里的refreshCheckTime表示自Token颁发后，超过20分钟，就为请求更新一次Token，同时，
     * 我们的refreshCheckTime时间应当小于令牌的有效期tokenExpireTime，即我们是要令牌在有效期内进行更新。
     * @param authorization
     * @param currentTimeMillis
     * @return
     */
    private boolean refreshCheck(String authorization, Long currentTimeMillis) {
        String tokenMillis = JwtUtil.getClaim(authorization, SecurityConsts.CURRENT_TIME_MILLIS);
       // TODO  3、这里要考虑到记住我功能，所以更新令牌时间也要改变！！ 错误更正：记没记住我和令牌刷新时间没有必然关系，只要令牌刷新时间refreshCheckTime在
        // TODO 令牌有效期tokenExpireTime之内，就都可以刷新，这里我们把记住我和没有记住我分别分配了两个key存入到Redis中，key的过期时间也设置的不同
        //当前时间戳-token中的时间戳 大于20分钟(即设置的更新令牌时间为20分钟)
        if (currentTimeMillis - Long.parseLong(tokenMillis) > (jwtProperties.refreshCheckTime * 10 * 1000L)) {
            return true;
        }
        return false;
    }

    /**
     * 是否允许访问
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //是否登录
        if (isLoginAttempt(request, response)) {
            try {
                //登录验证
                this.executeLogin(request, response);
                return true;
            } catch (Exception e) {
                String msg = e.getMessage();
                Throwable throwable = e.getCause();
                if (throwable != null && throwable instanceof SignatureVerificationException) {
                    msg = String.format("Token或者密钥不正确(%s)",throwable.getMessage());
                } else if (throwable != null && throwable instanceof TokenExpiredException) {
                    msg = String.format("Token已过期(%s)",throwable.getMessage());
                } else {
                    if (throwable != null) {
                        msg = throwable.getMessage();
                    }
                }
                //401非法请求 响应给前端
                this.response401(response, msg);
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 重写 onAccessDenied 方法，避免父类中调用再次executeLogin
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        logger.info("调用onAccessDenied拒绝访问");
        this.sendChallenge(request, response);
        return false;
    }

    /**
     * 401非法请求 （token过期）
     * @param resp
     * @param msg
     * 说明：当请求验证Token时抛出TokenExpiredException异常后，校验缓存中的RefreshToken的时间戳是否与当前请求Token时间戳一致，倘若一致，则重新生成Token，以当前时间戳更新缓存中的RefreshToken时间戳；倘若不一致，则以Json格式直接响应401未登录错误。
     * 采用前后端分离的方式，我们的401就需要直接返回JSON格式的响应。
     */
    private void response401(ServletResponse resp, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();

            Result result = new Result();
            result.setResult(false);
            result.setCode(Constants.TOKEN_CHECK_STALE_DATED);
            result.setMessage(msg);
            // 将结果响应给前端
            out.append(JSON.toJSONString(result));
        } catch (IOException e) {
            logger.error("返回Response信息出现IOException异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
