package com.rj.idempotency.token.annotation;

import java.lang.annotation.*;

/**
 * @Author: xtj
 * @Date: 2022/7/20 14:32
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatLimiter {
}
