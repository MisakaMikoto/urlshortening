package com.kakaopay.url.shortening.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "shorten_url")
public class ShortenURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shorten_url_id;
}
