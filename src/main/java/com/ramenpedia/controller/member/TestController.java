package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.entity.Store;
import com.ramenpedia.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private StoreRepository storeRepository;

    /**
     * 測試API，用於上版後測試是否正常連接DB
     * @return
     * @throws Exception
     */
    @GetMapping("/db")
    public ApiResponse<Store> hello() throws Exception {
        Store store = storeRepository.findOneForTest();
        return ApiResponse.getSuccessInstance(store);
    }
}
