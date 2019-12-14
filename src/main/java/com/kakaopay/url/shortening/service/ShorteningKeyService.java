package com.kakaopay.url.shortening.service;

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

    public Optional<ShorteningKey> findShorteningKey(String originUrl) {
        return this.shorteningKeyRepository.findByOriginUrl(originUrl);
    }

    public String createShorteningKey() {

        ShorteningKeyGenerator shorteningKeyGenerator = new ShorteningKeyGenerator();
        return String.valueOf(shorteningKeyGenerator.generate());
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
