package com.story.storyadmin.basic.reflect.annotation;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class Person {

    @NotNull
    public String name;

    @Range(max = 20)
    public int age;

    public Person(@NotNull String name, int age) {
        this.name=name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person（" + this.name + " ," + this.age + ")";
    }

    public static void main(String[] args) throws Exception {

        //通过反射判断@NotNull是否存在 两种方式判断
        Class cls=Person.class;
        // cls.isAnnotationPresent(Range.class);
        System.out.println(cls.isAnnotationPresent(Range.class));  //false???
        if (cls.isAnnotationPresent(Range.class)) {
            Range range = (Range) cls.getAnnotation(Range.class);
        } else {
            throw new NullPointerException();
        }

        //获取到Annotation注解
        Range range= (Range) cls.getAnnotation(Range.class);
        if (range != null) {
            int max = range.max();
            int min = range.min();
            System.out.println(max + " == " + min);
        } else {
            throw new NullPointerException();
        }


    }

}
