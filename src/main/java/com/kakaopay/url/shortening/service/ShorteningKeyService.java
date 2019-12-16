package com.kakaopay.url.shortening.service;

import com.kakaopay.url.shortening.entity.ShorteningKey;
import com.kakaopay.url.shortening.repository.ShorteningKeyRepository;
import com.kakaopay.url.shortening.service.impl.ShorteningKeyGeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShorteningKeyService {

    private final ShorteningKeyRepository shorteningKeyRepository;
    private final ShorteningKeyGeneratorServiceImpl shorteningKeyGeneratorServiceImpl;

    public Optional<ShorteningKey> findShorteningKey(String originUrl) {
        return this.shorteningKeyRepository.findByOriginUrl(originUrl);
    }

    public String createShorteningKey() {
        return String.valueOf(shorteningKeyGeneratorServiceImpl.generate());
    }

    @Transactional
    public ShorteningKey saveShorteningKey(String originUrl, String shorteningKey) {

        ShorteningKey shorteningKeyEntity = new ShorteningKey(originUrl, shorteningKey);
        return this.shorteningKeyRepository.save(shorteningKeyEntity);
    }

    public Optional<ShorteningKey> findOriginUrl(String shorteningKey) {
        return this.shorteningKeyRepository.findByShorteningKey(shorteningKey);
    }
}
