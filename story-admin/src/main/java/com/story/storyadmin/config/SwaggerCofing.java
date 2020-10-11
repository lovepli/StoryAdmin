package com.story.storyadmin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 */
@EnableSwagger2
@Configuration
public class SwaggerCofing extends WebMvcConfigurationSupport {

    /** 是否开启swagger */
    @Value("${swagger.enabled}")
    private boolean enabled;

    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    /**
     * 创建API
     * @return
     */
    @Bean
    public Docket apiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否启用Swagger
                .enable(enabled)
                // 设置哪些接口暴露给Swagger展示
                .select()
                //过滤的接口 ,扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.story.storyadmin.web")).paths(PathSelectors.any()).build()
                //定义分组
                .groupName("STORY-ADMIN 后端接口文档")
                //用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    /**
     * 添加摘要信息
     * @return
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：STORY-ADMIN管理系统_接口文档")
                // 描述
                .description("STORY-ADMIN's REST API")
                // 版本
                .version("1.0")
                // 作者信息
                .contact(new Contact("sunnj", "http://www.sundayfine.com", "sunnj87@163.com"))
//                .license("The Apache License, Version 2.0")//许可证信息
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//许可证地址
                .build();
    }

    /**
     *
     * 自定义的静态资源映射地址 https://www.cnblogs.com/sxdcgaq8080/p/7833400.html
     *
     * 1、项目相对目录:classpath:
     * 2、本地绝对目录:file:
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // swagger2页面
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 图片访问路径
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + mImagesPath);

    }
}