package com.convert.pdftoword.config.exception;

import com.convert.pdftoword.model.GlobalResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public GlobalResult<String> runtimeException(RuntimeException e){
        return new GlobalResult<>(500,"服务异常！");
    }

    @ExceptionHandler(GlobalException.class)
    public GlobalResult<String> globalException(GlobalException e) {
        return new GlobalResult<>(e.getErrCode(), e.getMessage());
    }

}
