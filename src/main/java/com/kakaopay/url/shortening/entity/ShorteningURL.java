package com.kakaopay.url.shortening.entity;

import lombok.Getter;

import javax.persistence.*;
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
public class ShorteningURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shortenUrlId;

    @Column
    String originUrl;

    @Column
    String shortenUrl;

    @Column
    LocalDateTime createdAt;
}
