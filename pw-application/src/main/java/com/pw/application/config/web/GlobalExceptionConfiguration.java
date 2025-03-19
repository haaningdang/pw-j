package com.pw.application.config.web;

import cn.hutool.core.util.StrUtil;
import com.pw.core.basic.response.PwResponse;
import com.pw.core.exception.PwAuthException;
import com.pw.core.exception.PwException;
import com.pw.validate.wrapper.PwValidationWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfiguration {

    /**
     * 参数校验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse methodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.error("trace={}", exception);
        BindingResult result = exception.getBindingResult();
        String message = PwValidationWrapper.wrapper(result);
        return PwResponse.failure(message);
    }

    /**
     * 参数校验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse bindException(BindException exception) {
        log.error("trace={}", exception);
        BindingResult result = exception.getBindingResult();
        String message = PwValidationWrapper.wrapper(result);
        return PwResponse.failure(message);
    }


    /**
     * 请求方法不支持异常
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("trace={}", exception);
        return PwResponse.failure("接口不支持当前请求的方式");
    }

    /**
     * 请求数据格式有误异常
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse mediaNotSupportedException(HttpMediaTypeNotSupportedException exception){
        log.error("trace={}", exception);
        return PwResponse.failure("接口不支持当前请求的方式");
    }

    /**
     * 接口不可达异常
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse httpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error("trace={}", exception);
        return PwResponse.failure("无法访问当前接口");
    }

    /**
     * 404访问异常
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse noHandlerFoundException(NoHandlerFoundException exception){
        log.error("trace={}", exception);
        return PwResponse.failure("访问的接口不可用");
    }

    /**
     * 一般异常
     * @param exception
     * @return
     */
    @ExceptionHandler(PwException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse serviceException(PwException exception){
        log.error("trace={}", exception);
        return PwResponse.failure(StrUtil.format(exception.getMessage()));
    }

    /**
     * 权限异常
     * @param exception
     * @return
     */
    @ExceptionHandler(PwAuthException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse authException(PwAuthException exception){
        log.error("trace={}", exception);
        return PwResponse.failure(-1, StrUtil.format(exception.getMessage()));
    }

    /**
     * 通用异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse exception(Throwable exception){
        log.error("request trace={}", exception);
        return PwResponse.failure("系统发生异常");
    }

}
