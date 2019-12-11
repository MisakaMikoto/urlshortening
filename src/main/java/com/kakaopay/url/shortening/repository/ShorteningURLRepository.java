package com.kakaopay.url.shortening.repository;

import com.kakaopay.url.shortening.entity.ShorteningURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShorteningURLRepository extends JpaRepository<ShorteningURL, Long> {

    ShorteningURL findByOriginUrl(String originUrl);
}
