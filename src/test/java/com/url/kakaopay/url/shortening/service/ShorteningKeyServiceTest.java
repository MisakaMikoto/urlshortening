package com.url.kakaopay.url.shortening.service;

import com.kakaopay.url.UrlshorteningApplication;
import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import com.kakaopay.url.shortening.entity.ShorteningKey;
import com.kakaopay.url.shortening.service.ShorteningKeyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureWebMvc
@SpringBootTest(classes={UrlshorteningApplication.class})
public class ShorteningKeyServiceTest {

    private final static int TEST_MAX_COUNT = 100;

    private final static int SHORTENING_MIN_LENGTH = 4;
    private final static int SHORTENING_MAX_LENGTH = 8;

    @Autowired
    private ShorteningKeyService shorteningKeyService;

    private static ShorteningKeyRequestDto shorteningKeyRequestDto = new ShorteningKeyRequestDto();

    @BeforeAll
    static void beforeAll() {

        String originUrl = "https://www.kakaopay.com/";
        shorteningKeyRequestDto.setOriginUrl(originUrl);
    }

    @Test
    void test_단축_키_만들기() {

        String generatedKey = shorteningKeyService.createShorteningKey();

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            boolean isContainsNumber = generatedKey.matches(".*\\d.*");

            Assertions.assertTrue(generatedKey.length() >= SHORTENING_MIN_LENGTH);
            Assertions.assertTrue(generatedKey.length() <= SHORTENING_MAX_LENGTH);
            Assertions.assertFalse(isContainsNumber);
        }
    }

    @Test
    void test_단축_키_저장() {

        String generatedKey = shorteningKeyService.createShorteningKey();
        shorteningKeyRequestDto.setShorteningKey(generatedKey);

        ShorteningKey shorteningKey =
                shorteningKeyService.saveShorteningKey(shorteningKeyRequestDto.getOriginUrl(), shorteningKeyRequestDto.getShorteningKey());

        Assertions.assertEquals(shorteningKeyRequestDto.getOriginUrl(), shorteningKey.getOriginUrl());
        Assertions.assertEquals(shorteningKeyRequestDto.getShorteningKey(), shorteningKey.getShorteningKey());
    }
}
