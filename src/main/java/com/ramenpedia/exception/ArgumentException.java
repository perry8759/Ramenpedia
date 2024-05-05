package com.ramenpedia.exception;

import com.ramenpedia.enumerate.ResponseConstant;

public class ArgumentException extends RuntimeException {

    private final ResponseConstant responseConstant;

    public ArgumentException(ResponseConstant responseConstant) {
        super(responseConstant.getMessage());
        this.responseConstant = responseConstant;
    }

    public Integer getCode() {
        return responseConstant.getCode();
    }

    public ResponseConstant getResponseConstant() {
        return responseConstant;
    }
}
