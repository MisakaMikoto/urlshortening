package com.kakaopay.url.shortening.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(
    name = "shorten_url",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"originUrl", "shorteningKey"}
        )
})
@NoArgsConstructor
public class ShorteningKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shortenUrlId;

    @NotNull
    String originUrl;

    @NotNull
    String shorteningKey;

    LocalDateTime createdAt;

    public ShorteningKey(String originUrl, String shorteningKey) {
        this.originUrl = originUrl;
        this.shorteningKey = shorteningKey;
        this.createdAt = LocalDateTime.now();
    }
}
