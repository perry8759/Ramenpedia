package com.ramenpedia.controller.collect.dto;

import com.ramenpedia.service.dto.StoreInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetCollectStoreInfo {
    private List<StoreInfo> storeInfoList;
}
