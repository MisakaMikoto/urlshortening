package com.kakaopay.url.shortening.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class ShorteningValidPointcut {

    @Pointcut("@annotation(com.kakaopay.url.shortening.annotation.ValidOriginUrl)")
    public void valid() {}
}
