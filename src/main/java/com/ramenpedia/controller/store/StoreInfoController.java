package com.ramenpedia.controller.store;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.controller.store.dto.AllStoreInfoReq;
import com.ramenpedia.controller.store.dto.AllStoreInfoResp;
import com.ramenpedia.controller.store.dto.StoreInfoReq;
import com.ramenpedia.controller.store.dto.StoreInfoResp;
import com.ramenpedia.service.StoreInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/store/info")
public class StoreInfoController {

    private final StoreInfoService storeInfoService;

    public StoreInfoController(StoreInfoService storeInfoService) {
        this.storeInfoService = storeInfoService;
    }

    @GetMapping
    public ApiResponse<StoreInfoResp> getStoreInfo(StoreInfoReq req) {
        req.valid();
        storeInfoService.getStoreInfo();
        return ApiResponse.getSuccessInstance();
    }

    @GetMapping("all")
    public ApiResponse<AllStoreInfoResp> getAllStoreInfo(AllStoreInfoReq req) {
        req.valid();
        AllStoreInfoResp resp = storeInfoService.getAllStoreInfo(req);
        return ApiResponse.getSuccessInstance(resp);
    }
}
