package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple;

import com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.extendChange.QuardRangle;
import com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.extendChange.Rectangle2;
import com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.extendChange.Square2;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Test {

    public static void resize(Rectangle rectangle){
        while (rectangle.getWidth() <= rectangle.getLength()){
            rectangle.setWidth(rectangle.getWidth()+1);
            System.out.println("width:"+rectangle.getWidth() + " length:"+rectangle.getLength());
        }
        System.out.println("resize方法结束 width:"+rectangle.getWidth() + " length:"+rectangle.getLength());
    }

    /**
     * 在测试长方形时，代码不会出现问题
     */
    public static void fun1(){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setLength(20);
        resize(rectangle);
    }

    /**
     * 但是将长方形换做正方形，程序就无法停止，一直循环。原因在于Square重写了Rectangle方法，正方形的特性使得程序出了问题。
     */
    public static void fun11(){
        Square rectangle = new Square();
        rectangle.setWidth(10);
        rectangle.setLength(20);
        resize(rectangle);
    }


    public static void resize2(Rectangle2 quardRangle) {
        while (quardRangle.getWidth() >= quardRangle.getLength()) {
            quardRangle.setLength(quardRangle.getLength() + 1);
            System.out.println("width=" + quardRangle.getWidth() +
                    "length=" + quardRangle.getLength());
        }
        System.out.println("Resize end,width=" + quardRangle.getWidth() +
                "length=" + quardRangle.getLength());
    }


    public static void fun2(){
        Rectangle2 rectangle = new Rectangle2();
        rectangle.setWidth(20);
        rectangle.setLength(10);
        resize2(rectangle);
    }




    public static void main(String[] args) {

       // fun1();
        //fun11();
       // fun2();


    }
}
