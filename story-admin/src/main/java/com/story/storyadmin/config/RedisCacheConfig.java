package com.story.storyadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * redis缓存配置
 * 自定义一个 RedisTemplate
 *
 *  Spring Boot 在 RedisAutoConfiguration 中默认配置了 RedisTemplate<Object, Object>、StringRedisTemplate两个模板类，
 *  然而RedisTemplate<Object, Object>并未指定key、value的序列化器
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**导入RedisConnectionFactory */
	@Autowired
	private RedisConnectionFactory connectionFactory;

    /**
     * 自定义一个 RedisTemplate<String, Object>模版类
     * @return RedisTemplate<String, Object>
     */
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
		redisTemplate.setConnectionFactory(connectionFactory);
        //RedisTemplate<String, Object>指定key、value的序列化器
        // Redis key 序列化
		redisTemplate.setKeySerializer(new StringRedisSerializer());
        // Redis value 序列化，这里没有设置value的序列化方式，就默认使用jdk默认的序列化方式
		//redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		return redisTemplate;
	}

    /**
     * 测试
     * 自定义一个 RedisTemplate<String, Object>模版类
     * 这个bean类是上面改写的，value使用json序列化存储到redis
     * @return RedisTemplate<String, Object>
     */
    @Bean(name = "redisTemplate2")
    public RedisTemplate<String, Object> redisTemplate2() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // jackson2JsonRedisSerializer是以JSON格式去存储数据
        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // Redis key 序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // Redis value 序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 自定义一个 RedisTemplate<String, String>模版类, 相当于是一个StringRedisTemplate模版类
     * 这个bean对象好像在项目中是没有用到过
     * @return RedisTemplate<String, String>
     */
	@Bean(name = "cacheRedisTemplate")
	public RedisTemplate<String, String> cacheRedisTemplate() {
		RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		// 设置redis连接Factory
		template.setConnectionFactory(connectionFactory);
        // jackson2JsonRedisSerializer是以JSON格式去存储数据，可以作为Redis的序列化方式
		Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);  

		ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        //value值采用json序列化
        //使用jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();  
		return template;
	}

    /**
     * 测试Bean1
     * 这个bean对象好像是我自己写的，在项目中是没有用到过
     * 相关问题：https://blog.csdn.net/bai_bug/article/details/81222519
     * https://juejin.im/post/5da024e8e51d4577ea077ef4
     * https://blog.csdn.net/xiaolyuh123/article/details/78682200
     * @param redisConnectionFactory 设置redis连接Factory
     * @return RedisTemplate<String, Serializable>
     */
    @Bean(name = "redisCacheTemplate")
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        //使用GenericJackson2JsonRedisSerializer序列化value
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //value hashmap序列化，
        // template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 测试Bean2  https://www.cnblogs.com/donfaquir/p/10594199.html
     * 自定义 存储String RedisTemplate<String, String>
     * @param factory
     * @return
     */
    @Bean(name = "strRedisTemplate")
    public RedisTemplate<String, String> strRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    /**
     * 测试Bean3
     * 自定义 存储Integer RedisTemplate<String, Integer>
     * @param factory
     * @return
     */
    @Bean(name = "intRedisTemplate")
    public RedisTemplate<String, Integer> intRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }


}
