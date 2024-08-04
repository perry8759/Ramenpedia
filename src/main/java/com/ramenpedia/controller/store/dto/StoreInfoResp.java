package com.ramenpedia.controller.store.dto;

import com.ramenpedia.service.dto.StoreInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreInfoResp {
    private Long storeId;
    /**
     * 商店名稱。
     */
    private String storeName;

    /**
     * 商店地址。
     */
    private String address;

    /**
     * 商店評分。
     */
    private double score;

    /**
     * 商店描述。
     */
    private String description;

    public StoreInfoResp(StoreInfo info) {
        this.storeId = info.getStoreId();
        this.storeName = info.getStoreName();
        this.address = info.getAddress();
        this.score = info.getScore();
        this.description = info.getDescription();
    }
}
