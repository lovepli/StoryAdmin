package com.story.storyadmin.config;

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
    @Bean
    public Docket apiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //过滤的接口
                .apis(RequestHandlerSelectors.basePackage("com.story.storyadmin.web")).paths(PathSelectors.any()).build()
                //定义分组
                .groupName("STORY-ADMIN 后端接口文档")
                //展示在文档页面信息内容
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("STORY-ADMIN API")
                //详细描述
                .description("STORY-ADMIN's REST API")
                .version("1.0")
                //作者
                .contact(new Contact("sunnj", "http://www.sundayfine.com", "sunnj87@163.com"))
//                .license("The Apache License, Version 2.0")//许可证信息
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//许可证地址
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}