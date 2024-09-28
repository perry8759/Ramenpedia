package com.ramenpedia.controller.store.dto;

import com.ramenpedia.enumerate.StoreBusinessStatus;
import com.ramenpedia.service.dto.StoreInfoDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

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

    /**
     * 商店圖片。
     */
    private String img;

    /**
     * 商店狀態。
     */
    private StoreBusinessStatus status;

    /**
     * 商店營業時間資料
     */
    private Map<Integer, List<BusinessHoursInfo>> businessHoursInfoList;

    public StoreInfoResp(StoreInfoDetail info) {
        this.storeId = info.getStoreId();
        this.storeName = info.getStoreName();
        this.address = info.getAddress();
        this.score = info.getScore();
        this.description = info.getDescription();
        this.img = info.getImg();
        this.status = info.getStatus();
        this.businessHoursInfoList = info.getBusinessHoursInfoMap();
    }
}
