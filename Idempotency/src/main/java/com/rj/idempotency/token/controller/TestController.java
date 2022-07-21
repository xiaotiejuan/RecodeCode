package com.rj.idempotency.token.controller;

import com.rj.idempotency.token.annotation.RepeatLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xtj
 * @Date: 2022/7/20 15:14
 */
@RestController
@RequestMapping("/token/test")
public class TestController {

    @GetMapping("/1")
    @RepeatLimiter
    public String test() {
        return "1";
    }
}
