package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/hello")
    public ApiResponse<String> hello() throws Exception {
        return ApiResponse.getSuccessInstance("hello");
    }
}
