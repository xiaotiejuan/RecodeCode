package com.rj.idempotency.token.service;

/**
 * @Author: xtj
 * @Date: 2022/7/20 10:39
 */
public interface TokenService {

    public Long getToken();

    /**
     * 删除记录，true表示第一次提交，false重复提交
     */
    public Boolean checkToken(long token);
}
