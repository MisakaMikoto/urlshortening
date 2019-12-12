package com.kakaopay.url.shortening.util;

import java.util.Random;

public class ShorteningKeyGenerator {

    private final static int SHORTENING_MAX_LENGTH = 8;

    private final static int ALPHABET_LENGTH = 26;
    private final static int ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX = 65;
    private final static int ALPHABET_LOWER_CASE_FIRST_ASCII_INDEX = 97;

    public char[] generate() {

        char[] shorteningURL = new char[SHORTENING_MAX_LENGTH];
        for(int i = 0; i < shorteningURL.length; i++) {
            if(this.isUpperCase()) {
                shorteningURL[i] = this.generateUpperCase();

            } else {
                shorteningURL[i] = this.generateLowerCase();
            }
        }
        return shorteningURL;
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
