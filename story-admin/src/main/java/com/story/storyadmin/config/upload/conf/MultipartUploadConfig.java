package com.story.storyadmin.config.upload.conf;

import com.story.storyadmin.config.upload.aop.MultipartHandlerInterceptor;
import com.story.storyadmin.config.upload.support.DefaultFileArchiveStrategy;
import com.story.storyadmin.config.upload.support.DefaultFileResolver;
import com.story.storyadmin.config.upload.support.FileArchiveStrategy;
import com.story.storyadmin.config.upload.support.FileResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 文件上传配置文件类
 * @author sunnj
 */
@Configuration
public class MultipartUploadConfig extends WebMvcConfigurationSupport {

    /**
     * 将拦截器注册到 spring 容器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MultipartHandlerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

//    @Bean
//    public MultipartAspect multipartAspect() {
//        return new MultipartAspect();
//    }


//    @Bean
//    @ConditionalOnMissingBean(StorageService.class)
//    public StorageService defaultStorageService() {
//        return new StorageServiceImpl();
//    }

    @Bean
    @ConditionalOnMissingBean(FileArchiveStrategy.class)
    public FileArchiveStrategy fileArchiveStrategy() {
        return new DefaultFileArchiveStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(FileResolver.class)
    public FileResolver defaultFileResolver() {
        return new DefaultFileResolver();
    }

}
