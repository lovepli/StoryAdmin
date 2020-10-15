package com.story.storyadmin.exception;

import com.story.storyadmin.common.exception.ApiException;
import com.story.storyadmin.constant.InformErrorCode;

/**
 * 具体的业务异常
 * @author lipan
 */
public class FaileIdException extends ApiException {
    public FaileIdException(String message){
        super(InformErrorCode.FaileIdErrorCode,message,null);
    }
}
