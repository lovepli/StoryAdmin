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
     * 将文件上傳拦截器注册到 spring 容器,用来指定静态资源不被拦截
     * 在该配置中重写 addInterceptors 方法，将我们上面自定义的拦截器MultipartHandlerInterceptor添加进去，addPathPatterns 方法是添加要拦截的请求，这里我们拦截所有的请求。这样就配置好拦截器了
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // // 实现WebMvcConfigurer不会导致静态资源被拦截
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
