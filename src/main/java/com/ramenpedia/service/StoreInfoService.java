package com.ramenpedia.service;

import com.ramenpedia.controller.store.dto.AllStoreInfoReq;
import com.ramenpedia.controller.store.dto.AllStoreInfoResp;
import com.ramenpedia.dao.StoreDao;
import com.ramenpedia.entity.Store;
import com.ramenpedia.repository.StoreRepository;
import com.ramenpedia.service.dto.StoreInfo;
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

    public void getStoreInfo() {
        log.info("getStoreInfo");
    }

    public AllStoreInfoResp getAllStoreInfo(AllStoreInfoReq req) {
        Page<Store> storeList = storeDao.getAllStore(req.getStoreName(), req.getHashtagList(), req.getPage(), req.getSize());

        List<StoreInfo> storeInfoList = storeList.getContent().stream().map(
                store -> new StoreInfo(store.getName(), store.getAddress(), store.getScore(), store.getDescription())
        ).toList();

        return new AllStoreInfoResp(storeInfoList, storeList.getTotalPages());
    }
}
