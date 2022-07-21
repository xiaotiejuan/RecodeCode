package com.rj.idempotency.token.service.impl;

import com.rj.idempotency.token.annotation.HeaderIntercept;
import com.rj.idempotency.token.cache.MyCacheConfiguration;
import com.rj.idempotency.token.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rj.idempotency.token.service.TokenService;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public Long getToken() {
        //获取全局唯一id
        long nextId = SnowFlake.getInstance().nextId();
        MyCacheConfiguration.put(nextId, UUID.randomUUID().toString());
        return nextId;
    }

    /**
    * 删除记录，true表示第一次提交，false重复提交
    */
    @Override
    public Boolean checkToken(long token) {
        String s = MyCacheConfiguration.get(token);
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        MyCacheConfiguration.remove(token);
        return true;
    }



    @PostConstruct
    public void test() {

    }
}