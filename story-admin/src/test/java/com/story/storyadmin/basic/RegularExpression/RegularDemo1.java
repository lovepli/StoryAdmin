package com.story.storyadmin.basic.RegularExpression;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class RegularDemo1 { 
    // 在正则表达式中 \d 表示0～9  但是Java中\d 表示为转义符，所以 \\d 在Java中表示0～9

    public static boolean is19xx(String s) {
        if (s == null || s == "" || s == " ") {
            return false;
        }
        return s.matches("19\\d\\d");
    }

}
