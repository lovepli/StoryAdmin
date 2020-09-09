package com.story.storyadmin.constant;

/**
 * auth相关的常量
 */
public class SecurityConsts {

    /**
     * 密码盐
     */
    public static final String LOGIN_SALT = "story-admin";

    /**
     * request请求头属性
     */
    public static final String REQUEST_AUTH_HEADER="Authorization";

    /**
     * JWT-account
     * 账户名
     */
    public static final String ACCOUNT = "account";

    /**
     * JWT-currentTimeMillis
     * 当前时间戳
     */
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     * 组织ID
     */
    public static final String ORG_ID_TOKEN = "orgIdToken";

    /**
     * Shiro redis 前缀
     * redis-key-前缀 story-admin:cache:
     */
    public static final String PREFIX_SHIRO_CACHE = "story-admin:cache:";

    /**
     * redis-key-前缀 story-admin:refresh_token:
     * token缓存时间戳的key
     */
    public final static String PREFIX_SHIRO_REFRESH_TOKEN = "story-admin:refresh_token:";

    /**
     * 定义检查Token刷新的redis Key。
     * redis-key-前缀 story-admin:refresh_check:
     */
    public final static String PREFIX_SHIRO_REFRESH_CHECK = "story:refresh_check:";



}
