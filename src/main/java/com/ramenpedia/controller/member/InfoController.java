package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.base.BaseController;
import com.ramenpedia.controller.member.dto.GetMemberInfoResp;
import com.ramenpedia.controller.member.dto.PatchMemberInfoReq;
import com.ramenpedia.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member/info")
public class InfoController extends BaseController {

    @Autowired
    private MemberInfoService memberInfoService;

    /**
     * Get member info
     */
    @GetMapping
    public ApiResponse<GetMemberInfoResp> getInfo(JwtAuthenticationToken jwtToken) {
        return ApiResponse.getSuccessInstance(memberInfoService.getInfo(getEmail(jwtToken)));
    }


    /**
     * Update member info
     */
    @PatchMapping
    public ApiResponse<String> patchInfo(JwtAuthenticationToken jwtToken, @RequestBody PatchMemberInfoReq req) {
        memberInfoService.patchInfo(getEmail(jwtToken), req.getNickname(), req.getImg(), req.getHashtags(),
                req.getActiveAreas());
        return ApiResponse.getSuccessInstance();
    }
}
