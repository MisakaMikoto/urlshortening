package com.kakaopay.url.shortening.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShorteningKeyRequestDto {

    @NotNull
    String originUrl;

    String shorteningKey;
}
