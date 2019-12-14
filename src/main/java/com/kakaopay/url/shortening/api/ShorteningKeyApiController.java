package com.kakaopay.url.shortening.api;

import com.kakaopay.url.shortening.annotation.ValidOriginUrl;
import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import com.kakaopay.url.shortening.dto.response.ShorteningKeyResponseDto;
import com.kakaopay.url.shortening.entity.ShorteningKey;
import com.kakaopay.url.shortening.service.ShorteningKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShorteningKeyApiController {

    private final ShorteningKeyService shorteningKeyService;

    @PostMapping("/shortening-key")
    @ValidOriginUrl
    public ResponseEntity generateShortenKey(HttpServletRequest httpServletRequest,
                                             @RequestBody @Valid ShorteningKeyRequestDto shorteningKeyRequestDto) throws IOException {

        Optional<ShorteningKey> optionalShorteningKey =
                this.shorteningKeyService.findShorteningKey(shorteningKeyRequestDto.getOriginUrl());

        String generatedKey;
        if (optionalShorteningKey.isPresent()) {
            generatedKey = optionalShorteningKey.get().getShorteningKey();

        } else {
            generatedKey = this.shorteningKeyService.createShorteningKey();
            generatedKey = this.getDomainUrl(httpServletRequest) + generatedKey;
            this.shorteningKeyService.saveShorteningKey(shorteningKeyRequestDto.getOriginUrl(), generatedKey);
        }
        ShorteningKeyResponseDto shorteningKeyResponseDto = new ShorteningKeyResponseDto();
        shorteningKeyResponseDto.setShorteningKey(generatedKey);

        return ResponseEntity.status(HttpStatus.OK).body(shorteningKeyResponseDto);
    }

    @GetMapping("/redirecting-key")
    public ResponseEntity redirectShortenKey(@RequestParam("shorteningKey") @NotNull String shorteningKey) throws IOException {

        ShorteningKeyResponseDto shorteningKeyResponseDto = new ShorteningKeyResponseDto();
        Optional<ShorteningKey> optionalShorteningKey =
                this.shorteningKeyService.findOriginUrl(shorteningKey);

        if(optionalShorteningKey.isPresent()) {
            shorteningKeyResponseDto.setOriginUrl(optionalShorteningKey.get().getOriginUrl());
        }
        return ResponseEntity.status(HttpStatus.OK).body(shorteningKeyResponseDto);
    }

    private String getDomainUrl(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURL().toString().split(httpServletRequest.getRequestURI())[0];
    }
}
