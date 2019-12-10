package com.kakaopay.url.shortening.shortenurl.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "shorten_url")
public class ShortenURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shorten_url_id;
}
