package com.tj.rateLimiter.service;

/**
 * @Author: xtj
 * @Date: 2022/7/21 10:28
 */
public interface MyRateLimiter {
    public  boolean tryAcquire();
}
