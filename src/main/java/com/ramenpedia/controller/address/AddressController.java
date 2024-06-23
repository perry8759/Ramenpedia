package com.ramenpedia.controller.address;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.controller.address.dto.GetCityResp;
import com.ramenpedia.controller.address.dto.GetDistrictReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/address")
public class AddressController {

    @GetMapping("city")
    public ApiResponse<GetCityResp> getCity() {
        return ApiResponse.getSuccessInstance();
    }

    @GetMapping("district")
    public ApiResponse<GetCityResp> getDistrict(GetDistrictReq req) {
        req.valid();
        return ApiResponse.getSuccessInstance();
    }
}
