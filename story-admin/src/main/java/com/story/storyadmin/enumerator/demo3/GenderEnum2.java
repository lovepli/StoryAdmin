package com.story.storyadmin.enumerator.demo3;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 方法二：枚举类转化为json字符串  参考：https://blog.csdn.net/DaiGG_/article/details/115484791?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-1.control&spm=1001.2101.3001.4242
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenderEnum2 {

    UNKNOWN(0, "未知"),

    MALE(1, "男"),

    FEMALE(2, "女");


    @JsonProperty("value") // 设置输出后的名称
    private final Integer code;

   @JsonProperty("label") // 设置输出后的名称
    private final String description;

    GenderEnum2(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(GenderEnum2.MALE);
            // 输出字符串 {"value":1,"label":"男"}
            System.out.println(s);
            String s2 = objectMapper.writeValueAsString(GenderEnum2.values());
            System.out.println(s2);
            //[{"value":0,"label":"未知"},{"value":1,"label":"男"},{"value":2,"label":"女"}]
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
