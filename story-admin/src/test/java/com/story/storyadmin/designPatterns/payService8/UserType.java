package com.story.storyadmin.designPatterns.payService8;


/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public enum UserType {

    SILVER_VIP(1, "白银会员"), SILVER_NOMAL(2, "普通会员");

    int value;
    String name;

    /**
     * 如果要为每个枚举值指定属性，则在枚举中必须声明一个参数为属性对应类型的构造方法（不能是public）。
     * 否则编译器将给出The constructor TypeEnum(int, String) is undefined的错误
     */
    UserType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    /**
     * 遍历枚举
     * @param value
     * @return
     */
    public static UserType getByValue(int value) {
        for (UserType typeEnum : UserType.values()) {
            if (typeEnum.value == value) {
                return typeEnum;
            }
        }
        throw new IllegalArgumentException("No element matches " + value);
    }


}
