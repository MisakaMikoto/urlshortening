package com.kakaopay.url.shortening.shortenurl.entity;

import com.kakaopay.url.shortening.url.entity.URL;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ShortenURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shorten_url_id;

    @OneToOne(fetch = FetchType.LAZY)
    URL url;
}
