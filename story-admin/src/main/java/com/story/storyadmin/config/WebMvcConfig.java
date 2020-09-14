package com.story.storyadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 开启 mvc支持，设置 static 目录为类路径
 */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    // 得到 classpath 的根路径， resources 目录下的所以路径都可以得到
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }
//}

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
