package com.kakaopay.url.shortening.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(
    name = "shorten_url",
    uniqueConstraints = {
    @UniqueConstraint(
        columnNames = {"originUrl", "shortenUrl"}
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
    String shortenUrl;

    LocalDateTime createdAt;

    public ShorteningKey(String originUrl, String shorteningUrl) {
        this.originUrl = originUrl;
        this.shortenUrl = shorteningUrl;
        this.createdAt = LocalDateTime.now();
    }
}
