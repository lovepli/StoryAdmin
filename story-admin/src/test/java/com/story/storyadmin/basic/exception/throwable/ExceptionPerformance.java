package com.story.storyadmin.basic.exception.throwable;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description: 测试比较try catch 和if else 的代码执行性能  结果使用try catch的代码执行时间较长
 * Java异常处理消耗性能的地方；
 * 1。try catch块影响JVM的优化
 * 2。异常对像实例需要保存栈快照等信息，开销较大
 *
 * 首先要清楚，如果没有try的话，出现异常会导致程序崩溃。而try则可以保证程序的正常运行下去
 * java中try....catch的使用原则:
 * 1.当代码中可能会遇到的异常需要用try{ }catch{Exception e}来处理，否则导致程序崩溃
 * 2.不要在for循环里面嵌套try catch子句
 * 3.尽可能的减少try catch子句的嵌套，会影响性能
 * 4.同一个try子句中多个catch时，异常处理原则:
 * 类异常放在你父类异常的前面来捕获，或者把最小范围异常放在最前面 ，范围大的放在后面
 *
 */
public class ExceptionPerformance {
    public static void testException(String[] array) {
        try {
            System.out.println(array[0]);
        } catch (NullPointerException e) {
            //e.printStackTrace();
            System.out.println("array cannot be null.");
        }
    }

    public static void testIf(String [] array) {
        if (array != null) {
            System.out.println(array[0]);
        } else {
            System.out.println("array cannot be null.");
        }
    }

    public static void main(String[] args) {
        long start =System.nanoTime();
        testException(null);
        //testIf(null);
        System.out.println("cost "+(System.nanoTime()-start));
    }

}
