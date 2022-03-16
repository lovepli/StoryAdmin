package com.story.storyadmin.framework.classLoader;

import lombok.Data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;


/**
 * @program: springBootPractice
 * @description:
 * @author: hu_pf
 * @create: 2019-07-13 00:09
 **/
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class FileDefine {

    public static String WATCH_PACKAGE = System.getProperty("user.home")+"/Documents/GitHub/StoryAdmin/story-admin/src/main/java/com/story/storyadmin/framework/classLoader/watchfile";

    private String fileName;

    private Long lastDefine;

    //public static void main(String[] args) {
    //    System.out.println(System.getProperty("user.home"));
    //}

}
