package com.story.storyadmin.config;


import com.story.storyadmin.Interceptor.demo2.FangshuaInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 我的网盘地址
     */
    @Value("${oasys.upload.location}")
    private String location;

    @Autowired
    private FangshuaInterceptor interceptor;

    /**
     * 在该配置中重写 addInterceptors 方法，将我们上面自定义的拦截器MyInterceptor添加进去，addPathPatterns 方法是添加要拦截的请求，这里我们拦截所有的Controller请求。这样就配置好拦截器了
     * @param registry
     */
    //@Override
    //protected void addInterceptors(InterceptorRegistry registry) {
    //   // registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");// 全局拦截器
    //    registry.addInterceptor(interceptor);// 接口防刷拦截器
    //    super.addInterceptors(registry);
    //}

    /**
     * 用来指定静态资源不被拦截，否则继承WebMvcConfigurationSupport这种方式会导致静态资源无法直接访问
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
        // 存放用户图像地址  图片放到/D:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+mImagesPath);
        // 配置我的网盘下载地址，这个地址就不需要再在shiro那里进行配置了
        registry.addResourceHandler("/upload/file/**").addResourceLocations("file:" + location);

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

/**
 * 1.3 解决静态资源被拦截问题
 * 上文中已经介绍了拦截器的定义和配置，但是这样是否就没问题了呢？其实不然，如果使用上面这种配置的话，我们会发现一个缺陷，那就是静态资源被拦截了。可以在 resources/static/ 目录下放置一个图片资源或者 html 文件，然后启动项目直接访问，即可看到无法访问的现象。
 *
 * 也就是说，虽然 Spring Boot 2.0 废弃了WebMvcConfigurerAdapter，但是 WebMvcConfigurationSupport 又会导致默认的静态资源被拦截，这就需要我们手动将静态资源放开。
 *
 * 如何放开呢？除了在 MyInterceptorConfig 配置类中重写 addInterceptors 方法外，还需要再重写一个方法：addResourceHandlers，将静态资源放开：
 *
 *
 *      **用来指定静态资源不被拦截，否则继承WebMvcConfigurationSupport这种方式会导致静态资源无法直接访问
 *      @Override
 *     protected void addResourceHandlers(ResourceHandlerRegistry registry){
 *      registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
 *      super.addResourceHandlers(registry);
 *      }
 *
 * 这样配置好之后，重启项目，静态资源也可以正常访问了。如果你是个善于学习或者研究的人，那肯定不会止步于此，没错，上面这种方式的确能解决静态资源无法访问的问题，但是，还有更方便的方式来配置。
 * 我们不继承 WebMvcConfigurationSupport 类，直接实现 WebMvcConfigurer 接口，然后重写 addInterceptors 方法，将自定义的拦截器添加进去即可，如下：
 *
 * @Configuration
 * public class MyInterceptorConfig implements WebMvcConfigurer {
 *     @Override
 *     public void addInterceptors(InterceptorRegistry registry) {
 *         // 实现WebMvcConfigurer不会导致静态资源被拦截
 *         registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
 *     }
 * }
 *
 * 这样就非常方便了，实现 WebMvcConfigure 接口的话，不会拦截 Spring Boot 默认的静态资源。
 * 这两种方式都可以，具体他们之间的细节，感兴趣的读者可以做进一步的研究，由于这两种方式的不同，继承 WebMvcConfigurationSupport 类的方式可以用在前后端分离的项目中，后台不需要访问静态资源（就不需要放开静态资源了）；实现 WebMvcConfigure 接口的方式可以用在非前后端分离的项目中，因为需要读取一些图片、css、js文件等等。
 *
 */

