package com.url.kakaopay.url.shortening.service;

import com.kakaopay.url.UrlshorteningApplication;
import com.kakaopay.url.shortening.service.ShorteningKeyGeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@AutoConfigureWebMvc
@SpringBootTest(classes={UrlshorteningApplication.class})
public class ShorteningKeyGeneratorServiceTest {

    private final static int TEST_MAX_COUNT = 100;

    private final static int SHORTENING_MIN_LENGTH = 4;
    private final static int SHORTENING_MAX_LENGTH = 8;

    private final static int ALPHABET_LENGTH = 26;
    private final static int ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX = 65;
    private final static int ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX = 97;

    @Autowired
    private ShorteningKeyGeneratorService shorteningKeyGeneratorService;

    @Test
    void test_단축_키_생성하기() {

        char[] generatedKey = shorteningKeyGeneratorService.generate();
        String strGenerateKey = String.valueOf(generatedKey);

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            boolean isContainsNumber = strGenerateKey.matches(".*\\d.*");

            Assertions.assertTrue(generatedKey.length >= SHORTENING_MIN_LENGTH);
            Assertions.assertTrue(generatedKey.length <= SHORTENING_MAX_LENGTH);
            Assertions.assertFalse(isContainsNumber);
        }
    }

    @Test
    void test_랜덤_대문자_생성하기() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        // private method test
        Method method = shorteningKeyGeneratorService.getClass().getDeclaredMethod("generateUpperCase");
        method.setAccessible(true);
        char upperCase = (char) method.invoke(shorteningKeyGeneratorService);

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            Assertions.assertTrue(ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX <= upperCase);
            Assertions.assertTrue(ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX + ALPHABET_LENGTH >= upperCase);
        }
    }

    @Test
    void test_랜덤_소문자_생성하기() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // private method test
        Method method = shorteningKeyGeneratorService.getClass().getDeclaredMethod("generateLowerCase");
        method.setAccessible(true);
        char lowerCase = (char) method.invoke(shorteningKeyGeneratorService);

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            Assertions.assertTrue(ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX <= lowerCase);
            Assertions.assertTrue(ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX + ALPHABET_LENGTH >= lowerCase);
        }
    }
}
