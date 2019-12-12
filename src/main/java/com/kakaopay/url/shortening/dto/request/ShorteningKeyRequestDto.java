package com.kakaopay.url.shortening.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ShorteningKeyRequestDto {

    @NotNull
    String originUrl;

    @NotNull
    String shortenUrl;

    public ShorteningKeyRequestDto(String originUrl) {
        this.originUrl = originUrl;
    }
}
