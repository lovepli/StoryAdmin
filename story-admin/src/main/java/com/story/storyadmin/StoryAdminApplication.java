package com.story.storyadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.story.storyadmin.mapper")
public class StoryAdminApplication {

    /**
     * 主方法
     */
    public static void main(String[] args) {
        SpringApplication.run(StoryAdminApplication.class, args);
    }

}
