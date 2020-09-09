package com.story.storyadmin.config.shiro.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "token")
@Component
@Data
public class JwtProperties {

    /**
     * token过期时间，单位分钟
     * 将令牌过期时间设置为一周，并在每次用户打开Web应用程序并每隔一小时刷新令牌。如果用户超过一周没有打开过应用程序，那他们就需要再次登录，这是可接受的Web应用程序UX(用户体验)
     * 这里设置的过期时间是一天，即24小时，令牌刷新时间为2小时
     */
    Integer tokenExpireTime;

    /**
     * 更新令牌时间，单位分钟
     * 这里的refreshCheckTime表示自Token颁发后，超过2个小时，就为请求更新一次Token，同时，
     * 我们的refreshCheckTime时间应当小于令牌的有效期tokenExpireTime，即我们是要令牌在有效期内进行更新。
     */
    Integer refreshCheckTime;

    /**
     * Shiro缓存有效期，单位分钟
     */
    Integer shiroCacheExpireTime;

    /**
     * token加密密钥
     */
    String secretKey;
}
