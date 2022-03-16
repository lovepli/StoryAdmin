package com.story.storyadmin.common.exception.handler;

import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.utils.ruoyiutils.ServletUtils;
import com.story.storyadmin.web.BaseController;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @ControllerAdvice，这是spring MVC提供的一个特殊的切面处理。
 * @author
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    /**
     * 捕捉shiro的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handle401(ShiroException e) {
        logger.error("ShiroException:{}", e);
        return new Result<String>(false, e.getMessage(), null,ResultEnum.SHIRO_UNAUTHORIZED_EXCEPTION.getCode());
    }

    /**
     * 捕捉UnauthorizedException权限异常：接口没有权限异常，这个异常前端页面显示了，捕获到了
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result authorityException(UnauthorizedException ex) {
        logger.error("UnauthorizedException权限异常:{}", ex);
        String msg= ex.getMessage();
        String pattern = "\\[(.*?)\\]";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(msg);
        String funcCode;
        if (m.find()) {
            funcCode= m.group(1);
            // 响应给前端页面
            return new Result<String>(false, String.format("操作失败，权限不足，权限码：%s",funcCode), null,ResultEnum.API_DO_NOT_UNAUTHORIZED.getCode());
        }else{
            return new Result<String>(false, "操作失败，权限不足，请联系管理员", null,ResultEnum.API_DO_NOT_UNAUTHORIZED.getCode());
        }
    }



    /**
     * @ExceptionHandler(Exception.class)註解: 用在方法上面表示遇到这个异常就执行以下方法。
     *
     * 自定义异常
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e, HttpServletRequest request) {
         // 如果是自定义的业务异常
        if(e instanceof CustomException){
            logger.error("【自定义异常】:{}",e);
            CustomException customException = (CustomException)e;
            logger.error("位置:{} -> 错误信息:{}", customException.getMethod() ,e.getLocalizedMessage());
            return Result.error(Objects.requireNonNull(ResultEnum.getByCode(customException.getErrorCode())));
        }else{
            //如果是系统的异常，比如空指针这些异常
            logger.error("【系统异常】:{}",e);
            // 设置响应状态码
            return new Result<String>(false, "系统发生异常，请联系管理员！", e.getMessage(), getStatus(request).value());
        }
    }

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

    //#############################################补充其他几种异常类型20210723########################################

    // 参数格式异常处理
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result badRequestException(IllegalArgumentException exception) {
        logger.error("参数格式不合法,{}" + exception.getMessage());
        return new Result<String>(false, "参数格式不符！", null,HttpStatus.BAD_REQUEST.value());
    }

    // 参数缺失异常处理
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result badRequestException(Exception exception) {
        logger.error("缺少必填参数!,{}" + exception.getMessage());
        return new Result<String>(false, "缺少必填参数！", null,HttpStatus.BAD_REQUEST.value());
    }

    // 权限不足异常处理
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result badRequestException(AccessDeniedException exception) {
        logger.error("权限不足,{}" + exception.getMessage());
        return new Result<String>(false, "权限不足！", null,HttpStatus.FORBIDDEN.value());
    }

    // 空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleTypeMismatchException(NullPointerException ex) {
        logger.error("空指针异常，{}", ex.getMessage());
        return new Result<String>(false, "空指针异常！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * 20210728新增
     * 请求方法不被允许异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("请求方式不支持，{}", e.getMessage());
        return new Result<String>(false, "不支持' " + e.getMethod() + "'请求", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * @param e
     * @return Content-Type/Accept 异常
     * application/json
     * application/x-www-form-urlencoded
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error("Content-Type/Accept 异常，{}", e.getMessage());
        return new Result<String>(false, "Content-Type/Accept 异常！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * handlerMapping  接口不存在抛出异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundException(NoHandlerFoundException e) {
        logger.error("接口不存在异常，{}", e.getMessage());
        return new Result<String>(false, "接口不存在异常！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * 认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result UnNoException(AuthenticationException e) {
        logger.error("认证异常，{}", e.getMessage());
        return new Result<String>(false, "认证异常！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        //log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request)) {
            logger.error("权限校验失败，{}", e.getMessage());
            return new Result<String>(false, "权限校验失败！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error/unauth");
            return modelAndView;
        }
    }


    //@ExceptionHandler(Exception.class)
    //@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    //public Result handleUnexpectedServer(Exception ex) {
    //    logger.error("系统异常：", ex);
    //    return new Result<String>(false, "系统发生异常，请联系管理员！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    //}

    // 系统异常处理
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable(Throwable throwable) {
        logger.error("系统异常：", throwable);
        return new Result<String>(false, "系统异常，请联系管理员！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }



    /**
     * 捕捉自定义业务异常
     * @param request
     * @param ex
     * @return
     */
//    @ExceptionHandler(BusinessException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result BusinessException(HttpServletRequest request, Throwable ex) {
//        logger.error("业务异常Exception:{}", ex);
//        return new Result<String>(false, ex.getMessage(), null);
//    }




// ################################ （JRS 303 校验框架 ）处理参数校验异常 #################### 参考： https://gitee.com/lovepli_cn/validation-spring-boot-demo
    /**
     * 处理单个参数校验失败抛出的异常
     * 处理@RequestParam 参数校验不通过异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result validationError(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        //violations.forEach(x -> builder.append(x.getMessage()).append(";")); // 遍历方式一
        for (ConstraintViolation<?> item : violations) { // 遍历方式二
            builder.append( "\n" + item.getMessage()).append(";");
        }
        logger.error("参数校验异常:{}", builder.toString());
        return new Result<String>(false, builder.toString(), "参数异常，请稍后重试",ResultEnum.FORMAT_ERROR.getCode());
    }




    /**
     * 处理 json 请求体调用接口对象参数校验失败抛出的异常
     * 处理@RequestBody校验异常，处理对象类型的数据的校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        StringBuffer sb = new StringBuffer();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        //allErrors.forEach(msg -> sb.append(msg.getDefaultMessage()).append(";")); // 遍历方式一
        for (ObjectError item : allErrors) {
            sb.append( "\n" + item.getDefaultMessage()).append(";");
        }
        logger.error("对象参数校验异常:{}", sb.toString());
        return new Result<String>(false, sb.toString(), "参数异常，请稍后重试",ResultEnum.FORMAT_ERROR.getCode());
    }

    /**
     * @param e
     * @return 处理 form data方式调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public Result formDaraParamsException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getField() + o.getDefaultMessage())
                .collect(Collectors.toList());
        logger.error("参数绑定校验异常:{}", collect);
        return new Result<String>(false, "参数绑定校验异常！", null,HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    /**
     *  @Validated + 全局捕获异常处理（最佳实践）
     *  测试时：使用 @Valid + 全局捕获异常处理也可以，具体区别有待研究。
     * 处理实体字段校验不通过异常
     */
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //public Result validationError(MethodArgumentNotValidException ex) {
    //    BindingResult result = ex.getBindingResult();
    //    final List<FieldError> fieldErrors = result.getFieldErrors();
    //    StringBuilder builder = new StringBuilder();
    //
    //    for (FieldError error : fieldErrors) {
    //        builder.append( "\n" + error.getDefaultMessage());
    //    }
    //    logger.error(builder.toString());
    //    return new Result<String>(false, builder.toString(), "参数异常，请稍后重试",ResultEnum.FORMAT_ERROR.getCode());
    //}


    //文件上传文件大小超出限制
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result fileSizeException(MaxUploadSizeExceededException exception) {
        logger.error("文件太大，上传失败",exception);
        return new Result<String>(false, "只允许上传不大于"+exception.getMaxUploadSize()+"的文件", null,ResultEnum.SERVER_ERROR.getCode());
    }


}
