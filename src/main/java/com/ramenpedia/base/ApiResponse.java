package com.ramenpedia.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ramenpedia.enumerate.ResponseConstant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiResponse<T> {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("content")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T content;

    public ApiResponse(Integer code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public static <T> ApiResponse<T> getSuccessInstance() {
        return new ApiResponse<>(ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getMessage(), null);
    }

    public static <T> ApiResponse<T> getSuccessInstance(T content) {
        return new ApiResponse<>(ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getMessage(), content);
    }

    public static <T> ApiResponse<T> getFailInstance(ResponseConstant responseConstant) {
        return new ApiResponse<>(responseConstant.getCode(), responseConstant.getMessage(), null);
    }

    public static <T> ApiResponse<T> getInvalidArgumentFailInstance(String message) {
        return new ApiResponse<>(ResponseConstant.INVALID_ARGUMENT.getCode(),
                String.format(ResponseConstant.INVALID_ARGUMENT.getMessage(), message), null);
    }
}
