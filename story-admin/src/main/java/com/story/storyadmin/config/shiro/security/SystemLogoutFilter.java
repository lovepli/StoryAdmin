package com.story.storyadmin.config.shiro.security;

import com.alibaba.fastjson.JSON;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.utils.JedisUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登出过滤器 退出登录时要清除redis中存储的jwt 用户token信息
 * SystemLogoutFilter 继承  shiro提供的LogoutFilter
 */
public class SystemLogoutFilter extends LogoutFilter {
    private  Logger logger = LoggerFactory.getLogger(SystemLogoutFilter.class);

    JedisUtils jedisUtils;

    public SystemLogoutFilter() {
    }

    public SystemLogoutFilter(JedisUtils jedisUtils) {
        this.jedisUtils = jedisUtils;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            //获取http请求头设置的属性Authorization的值authorization，即为jwt_token的值
            String authorization = httpServletRequest.getHeader(SecurityConsts.REQUEST_AUTH_HEADER);
            //从jwt-token中获取到用户账号信息account
            String account = JwtUtil.getClaim(authorization, SecurityConsts.ACCOUNT);

            //token用户信息是否存在  TODO story-admin:cache:account在什么时候进行了缓存？？？
            if(!StringUtils.isEmpty(account)){
                // 清除可能存在的token缓存信息 （退出登录，清除redis中保存的jwt token信息） tokenKey值为story-admin:cache:account
                String tokenKey = SecurityConsts.PREFIX_SHIRO_CACHE + account;
                //判断是否缓存了数据
                if (jedisUtils.exists(tokenKey)) {
                    jedisUtils.delKey(tokenKey);
                }
            }
            //退出登录
            subject.logout();
        } catch (Exception ex) {
            logger.error("退出登录错误",ex);
        }

        //响应json数据给前端
        this.writeResult(response);
        //不执行后续的过滤器
        return false;
    }

    private void writeResult(ServletResponse response){
        //响应Json结果
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Result result = new Result(true,null,null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
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
