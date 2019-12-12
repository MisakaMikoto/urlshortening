package com.url.kakaopay.url.shortening.util;

import com.kakaopay.url.shortening.util.ShorteningKeyGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class ShorteningKeyGeneratorTest {

    private final static int TEST_MAX_COUNT = 100;
    private final static int SHORTENING_MAX_LENGTH = 8;

    private final static int ALPHABET_LENGTH = 26;
    private final static int ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX = 65;
    private final static int ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX = 97;

    private static ShorteningKeyGenerator shorteningKeyGenerator;

    @BeforeAll
    static void beforeAll() {
        shorteningKeyGenerator = new ShorteningKeyGenerator();
    }

    @Test
    void test_단축_키_생성하기() {
        char[] generatedKey = shorteningKeyGenerator.generate();
        String strGenerateKey = String.valueOf(generatedKey);

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            boolean isContainsNumber = strGenerateKey.matches(".*\\d.*");

            assertEquals(generatedKey.length, SHORTENING_MAX_LENGTH);
            assertFalse(isContainsNumber);
        }
    }

    // private method test
    @Test
    void test_랜덤_대문자_생성하기() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Method method = shorteningKeyGenerator.getClass().getDeclaredMethod("generateUpperCase");
        method.setAccessible(true);
        char upperCase = (char) method.invoke(shorteningKeyGenerator);

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            assertTrue(ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX <= upperCase);
            assertTrue(ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX + ALPHABET_LENGTH >= upperCase);
        }
    }

    // private method test
    @Test
    void test_랜덤_소문자_생성하기() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = shorteningKeyGenerator.getClass().getDeclaredMethod("generateLowerCase");
        method.setAccessible(true);
        char lowerCase = (char) method.invoke(shorteningKeyGenerator);

        for(int i = 0; i < TEST_MAX_COUNT; i++) {
            assertTrue(ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX <= lowerCase);
            assertTrue(ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX + ALPHABET_LENGTH >= lowerCase);
        }
    }
}
