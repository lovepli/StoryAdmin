package com.story.storyadmin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.story.storyadmin.enumerator.demo3.Enumerator;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 在Spring Boot应用中我们希望能全局配置。Spring Boot的自动配置为我们提供了一个个性化定制ObjectMapper的可能性，
 * 你只需要声明一个Jackson2ObjectMapperBuilderCustomizer并注入Spring IoC:
 * 目标：实现Jackson类库序列化枚举对象为JSON 参考：https://mp.weixin.qq.com/s/Q5E059XI5cBfjKhiRnt0ug
 */

@Configuration
public class EnumGlobalConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer enumCustomizer(){
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(Enumerator.class, new JsonSerializer<Enumerator>() {
            @Override
            public void serialize(Enumerator value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                gen.writeNumberField("code",value.code());
                gen.writeStringField("description",value.description());
                gen.writeEndObject();
            }
        });
    }

}
