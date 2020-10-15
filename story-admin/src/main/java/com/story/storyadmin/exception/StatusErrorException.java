package com.story.storyadmin.exception;

import com.story.storyadmin.common.exception.ApiException;
import com.story.storyadmin.constant.InformErrorCode;

public class StatusErrorException extends ApiException {

    public  StatusErrorException(String message){
        super(InformErrorCode.StatusErrorCode,message,null);
    }
}
