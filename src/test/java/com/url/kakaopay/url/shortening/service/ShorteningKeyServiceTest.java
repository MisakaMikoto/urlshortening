package com.url.kakaopay.url.shortening.service;

import com.kakaopay.url.UrlshorteningApplication;
import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import com.kakaopay.url.shortening.service.ShorteningKeyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor
@SpringBootTest(classes={UrlshorteningApplication.class})
public class ShorteningKeyServiceTest {

    private final ShorteningKeyService shorteningKeyService;

    private static ShorteningKeyRequestDto shorteningKeyRequestDto;

    @BeforeAll
    static void beforeAll() {
        String originUrl = "https://www.naver.com";
        shorteningKeyRequestDto = new ShorteningKeyRequestDto(originUrl);
    }

    @Test
    void test_단축된_키_찾기() {
        this.shorteningKeyService.findShorteningKey(shorteningKeyRequestDto);
    }

    @Test
    void test_단축_키_만들기() {

    }

    @Test
    void test_단축_키_저장하기() {

    }
}
