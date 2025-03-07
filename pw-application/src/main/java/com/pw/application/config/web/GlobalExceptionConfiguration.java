package com.pw.application.config.web;

import cn.hutool.core.util.StrUtil;
import com.pw.core.basic.response.PwResponse;
import com.pw.core.exception.PwAuthException;
import com.pw.core.exception.PwException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfiguration {


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("trace={}", exception);
        return PwResponse.failure("接口不支持当前请求的方式");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse mediaNotSupportedException(HttpMediaTypeNotSupportedException exception){
        log.error("trace={}", exception);
        return PwResponse.failure("接口不支持当前请求的方式");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse httpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error("trace={}", exception);
        return PwResponse.failure("无法访问当前接口");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse noHandlerFoundException(NoHandlerFoundException exception){
        log.error("trace={}", exception);
        return PwResponse.failure("访问的接口不可用");
    }

    @ExceptionHandler(PwException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse serviceException(PwException exception){
        log.error("trace={}", exception);
        return PwResponse.failure(StrUtil.format(exception.getMessage()));
    }

    @ExceptionHandler(PwAuthException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse authException(PwAuthException exception){
        log.error("trace={}", exception);
        return PwResponse.failure(-1, StrUtil.format(exception.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PwResponse exception(Throwable exception){
        log.error("request trace={}", exception);
        return PwResponse.failure("系统发生异常");
    }

}
