package com.story.storyadmin.basic.exception.throwable.example;

/**
 * @author: lipan
 * @date: 2019-11-20
 * @description:
 */
public class TestException1 {

    //程序员a完善了自己的接口方法：
    public static ResponseDemo<Integer>  numberFormat(String num) {
        ResponseDemo<Integer> response = new ResponseDemo<>();
        try {
            int x = Integer.parseInt(num);
            response.setCode(100);
            response.setMessage("数据转化成功，已将值放入data下。");
            response.setData(x);
        } catch (Exception e) {
            response.setCode(101);
            response.setMessage("输入的字符串含有除了数字以外其他类型的格式，数据错误！");
        }
        return response;

    }

    /**
     * b呈现给客户c的方法。假设我们的打印是呈现给c的
     * @param args
     * @throws Exception
     */
    //然后程序员b也完善了自己的方法呈现给客户c：
    public static void main(String[] args){
        String num = "1234tr";
        ResponseDemo<Integer> reponse = numberFormat(num);
        System.out.println("客户c输入了错误的字符串，他也只会给程序员b返回这个对象："+reponse.toString());
        if(reponse.getCode() == 101) {
            System.out.println("程序员b通过对对象的处理，最后给客户c呈现了这样的信息："+reponse.getMessage());
        }else {
            //客户c看到了这个信息，发现是自己的输入的信息有误，输入了正确的字符串。123456，然后继续执行
            System.out.println(reponse.getData());
        }
    }
}
