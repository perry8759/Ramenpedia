package com.ramenpedia.service.dto;

import com.ramenpedia.controller.store.dto.BusinessHoursInfo;
import com.ramenpedia.enumerate.StoreBusinessStatus;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfoDetail {
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
     * 商店的開始營業時間
     */
    private Long openMillis;

    /**
     * 商店的結束營業時間
     */
    private Long closeMillis;

    /**
     * 商店狀態。
     */
    private StoreBusinessStatus status;

    /**
     * 商店營業時間資料
     */
    private Map<Integer, List<BusinessHoursInfo>> businessHoursInfoMap;
}
