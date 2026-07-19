package com.ramenpedia.controller.hello;

import com.ramenpedia.base.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("")
public class HelloController {

    @GetMapping("/hello")
    public ApiResponse<String> google(@RequestParam String req) {
        return ApiResponse.getSuccessInstance("Ni Hao");
    }

}
