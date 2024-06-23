package com.ramenpedia.exception;

import com.ramenpedia.enumerate.ResponseConstant;

public class ArgumentException extends RuntimeException {

    public ArgumentException(String message) {
        super(message);
    }
}
