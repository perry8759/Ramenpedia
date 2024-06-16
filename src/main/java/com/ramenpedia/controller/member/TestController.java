package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.entity.Member;
import com.ramenpedia.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/hello")
    public ApiResponse<String> hello() throws Exception {
        memberRepository.save(Member.create("test", "test", "test", ""));

        return ApiResponse.getSuccessInstance("hello");
    }
}
