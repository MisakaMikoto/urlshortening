package com.kakaopay.url.shortening.entity;

import lombok.Getter;
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
public class ShorteningKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shortenUrlId;

    @NotNull
    @Column
    String originUrl;

    @NotNull
    @Column
    String shortenUrl;

    @Column
    LocalDateTime createdAt;

    public ShorteningKey(String originUrl, String shorteningUrl) {
        this.originUrl = originUrl;
        this.shortenUrl = shorteningUrl;
        this.createdAt = LocalDateTime.now();
    }
}
