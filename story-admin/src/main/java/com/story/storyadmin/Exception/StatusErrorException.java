package com.story.storyadmin.Exception;

import com.story.storyadmin.common.ApiException;
import com.story.storyadmin.constant.InformErrorCode;

public class StatusErrorException extends ApiException {

    public  StatusErrorException(String message){
        super(InformErrorCode.StatusErrorCode,message,null);
    }
}
