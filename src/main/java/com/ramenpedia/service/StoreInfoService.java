package com.ramenpedia.service;

import com.ramenpedia.controller.store.dto.AllStoreInfoReq;
import com.ramenpedia.controller.store.dto.AllStoreInfoResp;
import com.ramenpedia.dao.StoreDao;
import com.ramenpedia.entity.Store;
import com.ramenpedia.repository.StoreRepository;
import com.ramenpedia.service.dto.StoreInfo;
import com.ramenpedia.service.dto.StoreInfoDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreInfoService {

    private final StoreRepository storeRepository;
    private final StoreDao storeDao;

    public StoreInfoService(StoreRepository storeRepository, StoreDao storeDao ) {
        this.storeRepository = storeRepository;
        this.storeDao = storeDao;
    }

    public StoreInfoDetail getStoreInfo(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        return new StoreInfoDetail(store.getId(), store.getName(), store.getAddress(), store.getScore(),
                store.getDescription(), store.getImg(), store.getOpenMillis(), store.getCloseMillis());
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