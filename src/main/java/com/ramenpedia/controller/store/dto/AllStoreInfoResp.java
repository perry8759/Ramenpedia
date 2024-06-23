package com.ramenpedia.controller.store.dto;

import com.ramenpedia.service.dto.StoreInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AllStoreInfoResp {
    List<StoreInfo> storeInfoList;
    private int totalPageSize;
}
