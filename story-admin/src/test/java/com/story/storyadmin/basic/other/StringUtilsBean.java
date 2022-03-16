package com.story.storyadmin.basic.other;

import com.story.storyadmin.utils.wind.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author: lipan
 * @date: 2020/5/19
 * @description: StringUtils类中isEmpty与isBlank的区别
 *
 * org.apache.commons.lang.StringUtils类提供了String的常用操作,最为常用的判空有如下两种isEmpty(String str)和isBlank(String str)。
 *
 */
public class StringUtilsBean {
    public static void main(String[] args){
        User user=new User();
        user.setName("张三");

        if (StringUtils.isBlank(user.getName())) {
           System.out.println("用户名为空！！");
        }else {
            System.out.println("hello world!");
        }
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private java.lang.Integer id;
    private String name;
    private java.lang.Integer age;
    private char sex;

}

//StringUtils.isEmpty(String str) 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0

//                 System.out.println(StringUtils.isEmpty(null));        //true
//                System.out.println(StringUtils.isEmpty(""));          //true
//                System.out.println(StringUtils.isEmpty("   "));       //false
//                System.out.println(StringUtils.isEmpty("dd"));        //false
//                StringUtils.isNotEmpty(String str) 等价于 !isEmpty(String str)

//StringUtils.isBlank(String str) 判断某字符串是否为空或长度为0或由空白符(whitespace) 构成

//               System.out.println(StringUtils.isBlank(null));        //true
//                System.out.println(StringUtils.isBlank(""));          //true
//                System.out.println(StringUtils.isBlank("   "));       //true
//                System.out.println(StringUtils.isBlank("dd"));        //false
//                StringUtils.isBlank(String str) 等价于 !isBlank(String str)


