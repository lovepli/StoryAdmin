package com.story.storyadmin.config.shiro.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.story.storyadmin.constant.SecurityConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * jwt帮助类：参考廖雪峰博客https://www.ruanyifeng.com/blog/2018/07/json_web_token-tutorial.html
 */
@Component
public class JwtUtil {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private static JwtUtil jwtUtil;

    @PostConstruct
    public void init() {
        jwtUtil = this;
        jwtUtil.jwtProperties = this.jwtProperties;
    }

    /**
     * 校验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        //密钥
        String secret = getClaim(token, SecurityConsts.ACCOUNT) + jwtUtil.jwtProperties.secretKey;
        //签名
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        //拿到的token取出account进行拼接制造token的部分身体段，再和解码后的token进行token比较token的结构内容，大致是这样的意思吧
        verifier.verify(token);
        return true;
    }

    /**
     * 获得Token中的信息,无需secret解密也能获得
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token, String claim) {
        try {
            //解码jwt token，获取解码后的jwt中的claim字符串信息，claim为你存储到jwt中的信息，
            // 从下面的sign方法可以看到，我们存入jwt中的claim包含account和currentTimeMillis信息，根据传入的claim实际参数不同，获取的数据不同
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名token,n分钟后过期
     * @param account
     * @param currentTimeMillis
     * @return
     */
    public static String sign(String account, String currentTimeMillis,Boolean rememberMe) {
        // secret密钥：通过帐号加JWT私钥加密
        String secret = account + jwtUtil.jwtProperties.getSecretKey();
        Date date =null;
        // 是否记住我
        if (rememberMe){
            // date是jwt_token过期时间，单位：毫秒  TODO 注意：这里设置的过期时间必须与登录时设置的refreshTokenKey缓存时间相等，这里单位是秒
            date = new Date(System.currentTimeMillis() + jwtUtil.jwtProperties.getTokenExpireTime()*60*1000L);
        }else {
            // 请求头中的token过期或者不存在，则报401错，需要重新跳转到登录页
            date = new Date(System.currentTimeMillis() + jwtUtil.jwtProperties.getTokenExpireTime()*1000L);
        }

        //HMAC256签名算法产生签名    secret是密钥
        Algorithm algorithm = Algorithm.HMAC256(secret);

        //jwt 包含四个部分
        // account 用户信息-账号，
        // currentTimeMillis 系统当前时间戳，
        // date jwt token过期时间，
        // algorithm签名
        return JWT.create()
                .withClaim(SecurityConsts.ACCOUNT, account)
                .withClaim(SecurityConsts.CURRENT_TIME_MILLIS, currentTimeMillis)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
