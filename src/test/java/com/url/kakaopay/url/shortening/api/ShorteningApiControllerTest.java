package com.url.kakaopay.url.shortening.api;

import com.kakaopay.url.UrlshorteningApplication;
import com.kakaopay.url.shortening.api.ShorteningKeyApiController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureWebMvc
@RequiredArgsConstructor
@SpringBootTest(classes={UrlshorteningApplication.class})
public class ShorteningApiControllerTest {

    private final MockMvc mockMvc;

    private final ShorteningKeyApiController ShorteningKeyApiController;


    @Test
    public void testShorteningKeyApiController( ) throws Exception {
//        this.mockMvc.perform(get("/v1/account/logout"))
//                .andDo(print())
//                .andExpect(status().isOk());
    }
}
