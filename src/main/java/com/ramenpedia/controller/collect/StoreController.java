package com.ramenpedia.controller.collect;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.base.BaseController;
import com.ramenpedia.controller.collect.dto.AddCollectStoreReq;
import com.ramenpedia.controller.collect.dto.DeleteCollectStoreReq;
import com.ramenpedia.controller.collect.dto.GetCollectStoreInfo;
import com.ramenpedia.service.CollectStoreService;
import com.ramenpedia.service.dto.StoreInfo;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("collect/store")
public class StoreController extends BaseController {

    private final CollectStoreService collectStoreService;

    public StoreController(CollectStoreService collectStoreService) {
        this.collectStoreService = collectStoreService;
    }

    /**
     * 取得用戶收藏的店家
     */
    @GetMapping
    public ApiResponse<GetCollectStoreInfo> getCollectStore(JwtAuthenticationToken jwtToken) {
        List<StoreInfo> collectStoreList = collectStoreService.getCollectStore(getEmail(jwtToken));
        return ApiResponse.getSuccessInstance(
                new GetCollectStoreInfo(collectStoreList)
        );
    }

    /**
     * 新增用戶收藏的店家
     */
    @PostMapping
    public ApiResponse<String> addCollectStore(JwtAuthenticationToken jwtToken,
                                               @RequestBody AddCollectStoreReq req) {
        req.valid();
        collectStoreService.addCollectStore(getEmail(jwtToken), req.getStoreId());
        return ApiResponse.getSuccessInstance();
    }

    /**
     * 刪除用戶收藏的店家
     */
    @DeleteMapping
    public ApiResponse<String> deleteCollectStore(JwtAuthenticationToken jwtToken,
                                                  @RequestBody DeleteCollectStoreReq req) {
        req.valid();
        collectStoreService.deleteCollectStore(getEmail(jwtToken), req.getStoreId());
        return ApiResponse.getSuccessInstance();
    }
}
