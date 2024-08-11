package com.ramenpedia.controller.member.dto;

import com.ramenpedia.base.BaseRequest;
import com.ramenpedia.exception.ArgumentException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoogleOauth2CheckReq extends BaseRequest {

    private String token;

    @Override
    public void valid() throws ArgumentException {
        if (token == null || token.isEmpty()) {
            throw new ArgumentException("token");
        }
    }
}
