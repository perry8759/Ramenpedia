package com.ramenpedia.handler;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.exception.ArgumentException;
import com.ramenpedia.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleException(Exception e) {
        return ApiResponse.getFailInstance(ResponseConstant.UNKNOWN_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Object> handleRuntimeException(BusinessException e) {
        return ApiResponse.getFailInstance(e.getResponseConstant());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ArgumentException.class)
    public ApiResponse<Object> handleRuntimeException(ArgumentException e) {
        return ApiResponse.getFailInstance(e.getResponseConstant());
    }
}
