package com.ramenpedia.controller.hashtag;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.controller.hashtag.dto.GetHashtagReq;
import com.ramenpedia.controller.hashtag.dto.GetHashtagResp;
import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.service.HashtagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("hashtag")
public class HashtagController {

    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("all")
    public ApiResponse<GetHashtagResp> getHashtag(GetHashtagReq req) {
        req.valid();
        List<Hashtag> hashtagList =  hashtagService.getHashtag(req.getType());
        return ApiResponse.getSuccessInstance(new GetHashtagResp(hashtagList));
    }
}
