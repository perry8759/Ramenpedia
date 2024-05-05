package com.ramenpedia.enumerate;

import lombok.Getter;

@Getter
public enum ResponseConstant {

    // 成功
    SUCCESS(0, "Success"),
    INVALID_ARGUMENT_TOKEN(1001, "Success"),
    UNKNOWN_ERROR(500, "Unknown error, please contact service");

    private final Integer code;
    private final String message;

    ResponseConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
