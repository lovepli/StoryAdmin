package com.story.storyadmin;

import com.story.storyadmin.filter.xss.EnableCrosXssFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.story.storyadmin.mapper")
@EnableCrosXssFilter// 增加自定义注解开关，需要使用的时候只需在springboot启动类加上@EnableCrosXssFilter注解即可开启 CrosXssFilter 进行防xss的过滤器的使用。
public class StoryAdminApplication {

    /**
     * 主方法
     */
    public static void main(String[] args) {
        SpringApplication.run(StoryAdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  StoryAdmin启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }

}
