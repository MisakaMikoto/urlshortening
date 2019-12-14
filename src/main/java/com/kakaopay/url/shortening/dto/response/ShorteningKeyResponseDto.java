package com.kakaopay.url.shortening.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShorteningKeyResponseDto {

    String originUrl;
    String shorteningKey;
}
