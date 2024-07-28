package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.base.BaseController;
import com.ramenpedia.service.PrivacyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/privacy-policy")
public class PrivacyPolicyController extends BaseController {

    @Autowired
    private PrivacyPolicyService privacyPolicyService;

    /**
     * 隱私權政策同意
     */
    @PostMapping("/assent")
    public ApiResponse<String> privacyPolicy(JwtAuthenticationToken jwtToken) {
        privacyPolicyService.privacyPolicy(getEmail(jwtToken));
        return ApiResponse.getSuccessInstance();
    }
}