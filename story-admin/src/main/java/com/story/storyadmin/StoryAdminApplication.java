package com.story.storyadmin;

import com.story.storyadmin.filter.xss.EnableCrosXssFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.story.storyadmin.mapper")
@EnableAsync // springboot 项目启动类上加@EnableAsync注解即可以实现方法的异步执行，无需再继承Thread类或实现Runable接口，在需要异步执行的方法上添加@Async注解，该方法即可以异步执行
@EnableCrosXssFilter// 增加自定义注解开关，需要使用的时候只需在springboot启动类加上@EnableCrosXssFilter注解即可开启 CrosXssFilter 进行防xss的过滤器的使用。
//@EnableScheduling // 在启动类上添加注解@EnableScheduling 开启定时任务
public class StoryAdminApplication { // extends SpringBootServletInitializer {

    /**
     * 主方法
     */
    public static void main(String[] args) {
        SpringApplication.run(StoryAdminApplication.class, args);
        //System.out.println("(♥◠‿◠)ﾉﾞ  StoryAdmin启动成功   ლ(´ڡ`ლ)ﾞ  ");
        System.out.println("   ヾ(◍°∇°◍)ﾉﾞ        START UP SUCCESS       ヾ(◍°∇°◍)ﾉﾞ   \n" +
                "        __________________      _     _            \n" +
                "       /  |    _     _    |    | |   | | _____    __    _       \n" +
                "  ____/___|   |_|   |_|   |    | |___| |/ ___  \\ |  \\  | |    \n" +
                " /   | |  |_______________|    |  ___  | |   | | | |\\\\ | |     \n" +
                "/____|_|__________________|    | |   | | |___|  \\| | \\\\| |    \n" +
                "    |_._|       |_._|          |_|   |_|______/\\_\\_|  \\__|   ");
    }

    /**
     * 将前端build文件放到后台项目的static文件夹中去，出现404
     * @param application
     * @return
     */
    //@Override
    //protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    //    return application.sources(StoryAdminApplication.class);
    //}



}
