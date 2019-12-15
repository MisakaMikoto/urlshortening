package com.kakaopay.url.shortening.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShorteningKeyGeneratorService {

    private final static int SHORTENING_MIN_LENGTH = 4;
    private final static int SHORTENING_MAX_LENGTH = 8;

    private final static int ALPHABET_LENGTH = 26;
    private final static int ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX = 65;
    private final static int ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX = 97;

    public char[] generate() {

        int randomShorteningLength = this.createRandomShorteningMaxLength();
        char[] shorteningURL = new char[randomShorteningLength];

        for(int i = 0; i < shorteningURL.length; i++) {
            if(this.isUpperCase()) {
                shorteningURL[i] = this.generateUpperCase();

            } else {
                shorteningURL[i] = this.generateLowerCase();
            }
        }
        return shorteningURL;
    }

    private int createRandomShorteningMaxLength() {
        return (int) (Math.random() * (SHORTENING_MAX_LENGTH - SHORTENING_MIN_LENGTH + 1)) + SHORTENING_MIN_LENGTH;
    }

    private boolean isUpperCase() {
        return new Random().nextBoolean();
    }

    private char generateUpperCase() {
        return (char)((int)(Math.random() * ALPHABET_LENGTH) + ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX);
    }

    private char generateLowerCase() {
        return (char)((int)(Math.random() * ALPHABET_LENGTH) + ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX);
    }

}
