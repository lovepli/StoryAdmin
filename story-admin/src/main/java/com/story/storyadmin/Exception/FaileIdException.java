package com.story.storyadmin.Exception;

import com.story.storyadmin.common.ApiException;
import com.story.storyadmin.constant.InformErrorCode;

public class FaileIdException extends ApiException {
    public FaileIdException(String message){
        super(InformErrorCode.FaileIdErrorCode,message,null);
    }
}
