package com.story.storyadmin.config.shiro.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义token
 * AuthenticationToken 在realm中作为参数，代表登录时的一种票据，我们把生成的jwt_token作为属性设置到JwtToken中，通过构造方法就获得了一个我们需要的AuthenticationToken类对象
 */
public class JwtToken implements AuthenticationToken {

    /**
     * jwt_token
     */
    private String token;

    /**
     * 构造函数 传入一个jwt_token属性 ，生成新的JwtToken对象
     * @param token
     */
    public JwtToken(String token) {
        this.token = token;
    }

    /**
     * 重写getPrincipal方法 返回token
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 重写getPrincipal方法 返回token
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
