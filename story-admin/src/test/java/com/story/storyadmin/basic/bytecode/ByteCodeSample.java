package com.story.storyadmin.basic.bytecode;

public class ByteCodeSample extends Test {


    @Override
    public void fun() {
        System.out.println("输出结果为222！");
    }


    public  static  void testDemo1(){
        int i=1,j=5;
        System.out.println(i++);//1
        System.out.println(++j);//6

        System.out.println(i);  //2
        System.out.println(j);  //6
    }

    public  static  void testDemo2(){
        int i=1,j=5;

        int x=(++i) + (i++) + (++i) + (i) +(i++);
        // x=2 + 2 + 4 + 4 + 4;
        System.out.println(x);  //16
        System.out.println(i);//5
    }
    public static void testDemo3() {


        System.out.println(10%20);  //10  求余数
        System.out.println(10/20);   //0

        System.out.println(10%5);  //0
        System.out.println(10/5);   //2  取整

    }


    //面试题
    public static void main(String[] args) {
      // testDemo1();
       testDemo2();
      // testDemo3();

         Test t=new ByteCodeSample();
         t.fun();  //输出结果为222！
         ByteCodeSample b=new ByteCodeSample();
         b.fun();  //输出结果为222！



    }



}

class Test {

    public void fun() {
        System.out.println("输出的结果为111！");
    }

}
