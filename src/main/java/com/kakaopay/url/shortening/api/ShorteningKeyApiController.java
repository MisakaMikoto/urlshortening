package com.kakaopay.url.shortening.api;

import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import com.kakaopay.url.shortening.entity.ShorteningKey;
import com.kakaopay.url.shortening.service.ShorteningKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/url/shortening")
@RequiredArgsConstructor
public class ShorteningKeyApiController {

    private final ShorteningKeyService shorteningKeyService;

    @PostMapping("/key")
    public ResponseEntity generateShortenKey(@RequestBody ShorteningKeyRequestDto shorteningKeyRequestDto) {

        Optional<ShorteningKey> optionalShorteningKey =
                this.shorteningKeyService.findShorteningKey(shorteningKeyRequestDto);

        String generatedKey;
        if(optionalShorteningKey.isPresent()) {
            generatedKey = optionalShorteningKey.get().getShortenUrl();

        } else {
            generatedKey = this.shorteningKeyService.createShortenKey();
            this.shorteningKeyService.saveShorteningKey(shorteningKeyRequestDto.getOriginUrl(), generatedKey);
        }
        return ResponseEntity.ok(generatedKey);
    }
}
