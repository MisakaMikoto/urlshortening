package com.kakaopay.url.shortening.api;

import com.kakaopay.url.shortening.service.ShorteningURLService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url/shortening")
@RequiredArgsConstructor
public class ShorteningURLApiController {

    private final ShorteningURLService shorteningURLService;

    @GetMapping("/{url}")
    public String getShortenUrl(@PathVariable(value = "url") String url) {
//        return this.shorteningURLService.getShortenUrl(url);
        return null;
    }
}
