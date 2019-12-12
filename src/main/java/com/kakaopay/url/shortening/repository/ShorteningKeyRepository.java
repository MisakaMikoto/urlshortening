package com.kakaopay.url.shortening.repository;

import com.kakaopay.url.shortening.entity.ShorteningKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShorteningKeyRepository extends JpaRepository<ShorteningKey, Long> {

    Optional<ShorteningKey> findByOriginUrl(String originUrl);
}
