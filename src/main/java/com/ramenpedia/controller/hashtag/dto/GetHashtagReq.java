package com.ramenpedia.controller.hashtag.dto;

import com.ramenpedia.base.BaseRequest;
import com.ramenpedia.enumerate.HashtagType;
import com.ramenpedia.exception.ArgumentException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetHashtagReq extends BaseRequest {
    private HashtagType type;

    @Override
    public void valid() throws ArgumentException {
        if (type == null) {
            throw new ArgumentException("type");
        }
    }
}
