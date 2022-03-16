package com.story.storyadmin.basic.assertion.demo.assertion;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
public class MessageUtil {
    private String message;

    //Constructor
    //@param message to be printed
    public MessageUtil(String message){
        this.message = message;
    }

    // prints the message
    public String printMessage(){
        System.out.println(message);
        return message;
    }
}
