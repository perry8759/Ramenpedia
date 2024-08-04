package com.ramenpedia.controller.store.dto;

import com.ramenpedia.base.BaseRequest;
import com.ramenpedia.exception.ArgumentException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreInfoReq extends BaseRequest {

    private Long storeId;

    @Override
    public void valid() throws ArgumentException {
        if (storeId == null) {
            throw new ArgumentException("storeId");
        }
    }
}
