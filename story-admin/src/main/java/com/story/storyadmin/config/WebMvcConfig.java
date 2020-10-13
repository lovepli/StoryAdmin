package com.story.storyadmin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 开启 mvc支持
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 本地文件存储路径
     */
    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    /**
     * 过滤
     * springboot中配置addResourceHandler和addResourceLocations，使得可以从磁盘中读取图片、视频、音频等
     * 访问路径：
     * 1.本地相对路径：classpath:
     * 2.本地绝对路径：file:
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 存放用户图像地址
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+mImagesPath);
    }
}

/**
 * https://blog.csdn.net/caidewei121/article/details/107646525
 * 配置后端服务的静态资源路径
 * 我们在创建好的 SpringBoot 项目中，资源目录专门存放在 resources 目录下，里面有个 static 目录和 templates 目录。这里我们会用到 static 目录作为我们的静态资源的路径。
 *
 * 我们在学习 SpringBoot 整合 SpringMVC 的时候，我们需要自己编写一个 配置类，来指定 SpringBoot 项目的静态资源的目录。配置类的编写如下
 *
 * 这样做的话，我们就可以在静态文件里面编写静态页面，并在后端服务显示了。但是我们的项目是 前后端分离的，所以就不会在 static 目录里面编写任何静态页面，我们把需要下载的静态资源放进去
 * 然后在外面，这个静态资源的请求路径就是 http://localhost:xxx/data/你的文件名称
 */
