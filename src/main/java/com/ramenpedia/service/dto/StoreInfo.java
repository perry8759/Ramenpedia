package com.ramenpedia.service.dto;

import lombok.*;
import org.checkerframework.checker.units.qual.N;

/**
 * 代表一個商店的基本信息。
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfo {

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
}
