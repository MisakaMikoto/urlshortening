package com.kakaopay.url.shortening.aspect;

import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Aspect
@Component
public class ShorteningValidAspect {

    @Around("com.kakaopay.url.shortening.aspect.pointcut.ShorteningValidPointcut.valid()")
    public Object beforeShortening(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] objects = proceedingJoinPoint.getArgs();
        ShorteningKeyRequestDto shorteningKeyRequestDto = this.findShorteningKeyRequestDto(objects);
        if(this.verifyRequestUrl(shorteningKeyRequestDto)) {
            return proceedingJoinPoint.proceed();

        } else {
            throw new IllegalStateException();
        }
    }

    private ShorteningKeyRequestDto findShorteningKeyRequestDto(Object[] objects) {

        ShorteningKeyRequestDto shorteningKeyRequestDto = new ShorteningKeyRequestDto();
        for(int i = 0; i < objects.length; i++) {
            if(objects[i] instanceof ShorteningKeyRequestDto) {
                shorteningKeyRequestDto = (ShorteningKeyRequestDto) objects[i];
                break;
            }
        }
        return shorteningKeyRequestDto;
    }

    private boolean verifyRequestUrl(ShorteningKeyRequestDto shorteningKeyRequestDto) throws IOException {

        boolean isVerify = false;

        if(shorteningKeyRequestDto.getOriginUrl().length() > 0) {
            URL url = new URL(shorteningKeyRequestDto.getOriginUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();

            isVerify = HttpURLConnection.HTTP_OK == responseCode;
        }
        return isVerify;
    }
}
