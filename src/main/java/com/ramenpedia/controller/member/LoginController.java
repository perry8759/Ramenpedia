package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.controller.member.dto.GoogleLoginReq;
import com.ramenpedia.controller.member.dto.GoogleLoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("member/login")
public class LoginController {


    @PostMapping("/google")
    public ApiResponse<GoogleLoginResp> google(@RequestBody GoogleLoginReq req) {
        req.valid();
        return ApiResponse.getSuccessInstance();
    }

}
