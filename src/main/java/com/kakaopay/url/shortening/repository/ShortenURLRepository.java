package com.kakaopay.url.shortening.repository;

import com.kakaopay.url.shortening.entity.ShortenURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenURLRepository extends JpaRepository<ShortenURL, Long> {
}
