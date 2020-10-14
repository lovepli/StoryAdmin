package com.story.storyadmin.web;

import com.story.storyadmin.common.ApiException;
import com.story.storyadmin.common.CustomException;
import com.story.storyadmin.domain.vo.Result;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 全局异常处理
 * @ControllerAdvice，这是spring MVC提供的一个特殊的切面处理。
 * @author
 */
@RestControllerAdvice
public class ExceptionController {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 捕捉shiro的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        logger.error("ShiroException:{}", e);
        return new Result<String>(false, e.getMessage(), null,401);
    }

    /**
     * 捕捉UnauthorizedException权限异常
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result authorityException(UnauthorizedException ex) {
        logger.error("UnauthorizedException", ex);
        String msg= ex.getMessage();
        String pattern = "\\[(.*?)\\]";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(msg);
        String funcCode;
        if (m.find()) {
            funcCode= m.group(1);
            return new Result<String>(false, String.format("操作失败，权限不足，权限码：%s",funcCode), null,401);
        }else{
            return new Result<String>(false, "操作失败，权限不足，请联系管理员", null,401);
        }
    }

    /**
     * 捕捉其他所有异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        logger.error("Exception:{}", ex);
        return new Result<String>(false, "系统异常，请稍后重试", null, getStatus(request).value());
    }

    /**
     * 捕捉其他所有异常
     * Handle exceptions thrown by handlers.
     */
//    @ExceptionHandler(value = Exception.class)
//    public Result exception(Exception exception, HttpServletResponse response) {
//
//        Result result =new Result();
//        if(exception instanceof ApiException){//api异常
//            ApiException apiException = (ApiException)exception;
//            result.setCode(apiException.getErrorCode());
//        }else{//未知异常
//            result.setCode(0);
//        }
//        result.setMessage(exception.getMessage());
//        // 设置响应状态码
//       // result.setCode(HttpStatus.valueOf(response.getStatus()));
//        return result;
//    }


    /**
     * 获取http状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


    /**
     * 捕捉业务异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result CustomException(HttpServletRequest request, Throwable ex) {
        logger.error("Exception:{}", ex);
        return new Result<String>(false, ex.getMessage(), null);
    }


}
