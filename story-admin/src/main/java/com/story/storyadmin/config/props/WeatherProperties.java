package com.story.storyadmin.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 第三方天气API配置参数信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "weather-config")
public class WeatherProperties {
    String appId;
    String appSecret;
}
