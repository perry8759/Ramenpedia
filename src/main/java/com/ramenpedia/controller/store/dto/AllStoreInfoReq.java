package com.ramenpedia.controller.store.dto;

import com.ramenpedia.base.BaseRequest;
import com.ramenpedia.exception.ArgumentException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AllStoreInfoReq extends BaseRequest {
    private String storeName;
    private List<Long> hashtagList;
    private int page;
    private int size;

    @Override
    public void valid() throws ArgumentException {
        if (page < 0) {
            throw new ArgumentException("page");
        }
        if (size < 0) {
            throw new ArgumentException("size");
        }
    }
}
