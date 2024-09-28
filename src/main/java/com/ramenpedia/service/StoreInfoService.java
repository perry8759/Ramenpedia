package com.ramenpedia.service;

import com.ramenpedia.controller.store.dto.AllStoreInfoReq;
import com.ramenpedia.controller.store.dto.AllStoreInfoResp;
import com.ramenpedia.controller.store.dto.BusinessHoursInfo;
import com.ramenpedia.dao.StoreDao;
import com.ramenpedia.entity.Store;
import com.ramenpedia.entity.StoreBusinessHours;
import com.ramenpedia.repository.StoreRepository;
import com.ramenpedia.service.dto.StoreInfo;
import com.ramenpedia.service.dto.StoreInfoDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoreInfoService {

    private final StoreRepository storeRepository;
    private final StoreDao storeDao;

    public StoreInfoService(StoreRepository storeRepository, StoreDao storeDao ) {
        this.storeRepository = storeRepository;
        this.storeDao = storeDao;
    }

    @Transactional
    public StoreInfoDetail getStoreInfo(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        Map<Integer, List<BusinessHoursInfo>> businessHoursInfoMap = new HashMap<>();
        for (StoreBusinessHours businessHours : store.getStoreBusinessHours()) {
            if (businessHoursInfoMap.containsKey(businessHours.getDayOfWeek())) {
                businessHoursInfoMap.put(businessHours.getDayOfWeek(), new ArrayList<>());
            }
            businessHoursInfoMap.get(businessHours.getDayOfWeek()).add(businessHours.getDayOfWeek(),
                    new BusinessHoursInfo(businessHours.getOpenTime(), businessHours.getCloseTime()));
        }

        StoreInfoDetail.Status status = switch (store.getStatus()) {
            case NORMAL -> StoreInfoDetail.Status.NORMAL;
            case CLOSED -> StoreInfoDetail.Status.CLOSED;
        };

        return new StoreInfoDetail(store.getId(), store.getName(), store.getAddress(), store.getScore(),
                store.getDescription(), store.getImg(), store.getOpenMillis(), store.getCloseMillis(), status, businessHoursInfoMap);
    }

    public AllStoreInfoResp getAllStoreInfo(AllStoreInfoReq req) {
        Page<Store> storeList = storeDao.getAllStore(req.getStoreName(), req.getHashtagList(), req.getPage(), req.getSize());

        List<StoreInfo> storeInfoList = storeList.getContent().stream().map(
                store -> new StoreInfo(store.getId(), store.getName(), store.getAddress(), store.getScore(),
                        store.getDescription(), store.getImg())
        ).toList();

        return new AllStoreInfoResp(storeInfoList, storeList.getTotalPages());
    }
}