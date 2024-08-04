package com.ramenpedia.enumerate;

import lombok.Getter;

@Getter
public enum ResponseConstant {

    INVALID_ARGUMENT(1000, "Invalid argument: %s"),

    MEMBER_NOT_FOUND(3000, "Member not found"),
    HASHTAG_NOT_FOUND(3001, "Hashtag not found"),
    STORE_ALREADY_IN_COLLECT_LIST(3002, "Store already in the collect list"),
    // 成功
    SUCCESS(0, "Success"),
    UNKNOWN_ERROR(500, "Unknown error, please contact service");

    private final Integer code;
    private final String message;

    ResponseConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
