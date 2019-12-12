package com.kakaopay.url.shortening.service;

import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import com.kakaopay.url.shortening.entity.ShorteningKey;
import com.kakaopay.url.shortening.repository.ShorteningKeyRepository;
import com.kakaopay.url.shortening.util.ShorteningKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShorteningKeyService {

    private final ShorteningKeyRepository shorteningKeyRepository;

    public Optional<ShorteningKey> findShorteningKey(ShorteningKeyRequestDto shorteningKeyRequestDto) {
        return this.shorteningKeyRepository.findByOriginUrl(shorteningKeyRequestDto.getOriginUrl());
    }

    public String shortenKey() {

        ShorteningKeyGenerator shorteningKeyGenerator = new ShorteningKeyGenerator();
        return String.valueOf(shorteningKeyGenerator.generate());
    }

    @Transactional
    public void saveShorteningKey(String originUrl, String shorteningKey) {

        ShorteningKey shorteningKeyEntity = new ShorteningKey(originUrl, shorteningKey);
        this.shorteningKeyRepository.save(shorteningKeyEntity);
    }
}
