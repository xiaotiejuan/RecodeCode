package com.rj.idempotency.token.controller;

import com.rj.idempotency.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xtj
 * @Date: 2022/7/20 15:09
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/get")
    public Long getToken() {
        return tokenService.getToken();
    }
}
