package com.kakaopay.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = "com.kakaopay.url" )
public class UrlshorteningApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlshorteningApplication.class, args);
    }

}
