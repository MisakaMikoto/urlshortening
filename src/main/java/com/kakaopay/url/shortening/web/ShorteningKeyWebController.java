package com.kakaopay.url.shortening.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ShorteningKeyWebController {

    @GetMapping("/url/shortening")
    public String index() {
        return "shortening/index";
    }
}
