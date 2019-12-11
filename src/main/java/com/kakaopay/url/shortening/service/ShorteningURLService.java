package com.kakaopay.url.shortening.service;

import com.kakaopay.url.shortening.entity.ShorteningURL;
import com.kakaopay.url.shortening.repository.ShorteningURLRepository;
import com.kakaopay.url.shortening.util.ShorteningURLGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShorteningURLService {

    private final static int ALPHABET_LENGTH = 26;

    private final static int ALPHABET_UPPER_CASE_FIRST_ASCII_INDEX = 65;
    private final static int ALPHABET_LOWWER_CASE_FIRST_ASCII_INDEX = 97;

    private final ShorteningURLRepository shorteningURLRepository;

    private String findShortenUrl(String originUrl) {
        Optional<ShorteningURL> optionalShorteningURL =
                Optional.ofNullable(this.shorteningURLRepository.findByOriginUrl(originUrl));

        return null;
    }

    private char[] shortenUrl() {

        ShorteningURLGenerator shorteningURLGenerator = new ShorteningURLGenerator();
        return shorteningURLGenerator.generate();
    }
}
