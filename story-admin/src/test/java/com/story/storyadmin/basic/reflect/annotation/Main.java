package com.story.storyadmin.basic.reflect.annotation;

import java.lang.reflect.Field;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * 使用反射API读取Annotation:
 * Class.isAnnotationPresent(Class)
 * Field.isAnnotationPresent(Class)
 * Method.isAnnotationPresent(Class)
 * Constructor.isAnnotationPresent(Class)
 * Class.getAnnotation(Class)
 * Field.getAnnotation(Class)
 * Method.getAnnotation(Class)
 * Constructor.getAnnotation(Class)
 * getParameterAnnotations()
 *
 * 可以通过工具处理注解来实现相应的功能:
 * 对JavaBean的属性值按规则进行检查
 * JUnit会自动运行@Test标记的测试方法
 */
public class Main {

    //通过注解来检查这些变量
    public static void main(String[] args)  throws Exception{
        Person p1 = new Person("xiao Ming", 25);
        Person p2 = new Person(null, 15);
        checkPerson(p1);
        checkPerson(p2);

    }

    private static void checkPerson(Person p) throws Exception {
        System.out.println("check " + p + "...");
        Class c = Person.class;
        for (Field f : c.getFields()) {
            checkField(f, p);
        }
    }

    private static void checkField(Field f, Person person) throws Exception{
        if (f.isAnnotationPresent(NotNull.class)) {
            Object r = f.get(person);
            if (r == null) {
                System.out.println("Error: field "+f.getName()+" is null!");
            }
        }
        if (f.isAnnotationPresent(Range.class)) {
            Range range = f.getAnnotation(Range.class);
            int n = (int) f.get(person);
            if (n < range.min() || n > range.max()) {
                System.out.println("Error: field " + f.getName() + " is outRange!");
            }
        }
    }
}
