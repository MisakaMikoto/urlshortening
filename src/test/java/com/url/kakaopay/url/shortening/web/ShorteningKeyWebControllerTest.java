package com.url.kakaopay.url.shortening.web;

import com.kakaopay.url.UrlshorteningApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes={UrlshorteningApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShorteningKeyWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String SHORTENING_KEY_WEB_CONTROLLER_URL = "/url/shortening";

    @Test
    public void test_단축키_WEB_컨트롤러_실행하기() throws Exception {

        this.mockMvc.perform(get(SHORTENING_KEY_WEB_CONTROLLER_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
