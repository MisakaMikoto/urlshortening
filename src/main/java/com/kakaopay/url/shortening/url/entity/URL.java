package com.kakaopay.url.shortening.url.entity;

import com.url.urlshortening.shortenurl.entity.ShortenURL;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long url_id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "url")
    @JoinColumn(name = "shorten_url_id")
    ShortenURL shortenURL;



}
