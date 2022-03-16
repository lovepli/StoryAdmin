package com.story.storyadmin.basic.exception.throwable;

import java.io.FileNotFoundException;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 */
public class ErrorAndException {
    private void throwError() {  //错误
        throw new StackOverflowError();
    }

    private void throwRuntimeException() { //受检查的异常
        throw new RuntimeException();
    }

    private void throwCheckedException() throws FileNotFoundException {//可检查的异常
//        try {
//            throw new FileNotFoundException();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        throw new FileNotFoundException();

    }

    public static void main(String[] args) throws FileNotFoundException{
        ErrorAndException eae = new ErrorAndException();

        //eae.throwError();
        //eae.throwRuntimeException();
        eae.throwCheckedException();
    }
}
