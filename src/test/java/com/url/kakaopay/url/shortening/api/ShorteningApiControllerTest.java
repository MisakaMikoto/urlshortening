package com.url.kakaopay.url.shortening.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kakaopay.url.UrlshorteningApplication;
import com.kakaopay.url.shortening.dto.request.ShorteningKeyRequestDto;
import com.kakaopay.url.shortening.dto.response.ShorteningKeyResponseDto;
import com.kakaopay.url.shortening.service.ShorteningKeyGeneratorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes={UrlshorteningApplication.class})
public class ShorteningApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShorteningKeyGeneratorService shorteningKeyGeneratorService;

    private static final String SHORTENING_KEY_GENERATE_URL = "/api/shortening-key";
    private static final String SHORTENING_KEY_REDIRECT_URL = "/api/redirecting-key";

    private static ShorteningKeyRequestDto shorteningKeyRequestDto = new ShorteningKeyRequestDto();

    @BeforeAll
    static void beforeAll() {

        String originUrl = "https://www.kakaopay.com/";
        shorteningKeyRequestDto.setOriginUrl(originUrl);
    }

    @Test
    public void test_단축키_생성_API_실행하기() throws Exception {

        String shorteningKeyRequestDtoJson = deserializeShorteningKeyRequestDto();
        this.mockMvc.perform(post(SHORTENING_KEY_GENERATE_URL)
                        .content(shorteningKeyRequestDtoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_단축키_리다이렉트_API_실행하기() throws Exception {

        String shorteningKeyRequestDtoJson = deserializeShorteningKeyRequestDto();
        MvcResult mvcResult = this.mockMvc.perform(post(SHORTENING_KEY_GENERATE_URL)
                .content(shorteningKeyRequestDtoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String shorteningKeyJson = mvcResult.getResponse().getContentAsString();
        ShorteningKeyResponseDto shorteningKeyResponseDto = this.serializeShorteningKey(shorteningKeyJson);

        this.mockMvc.perform(get(SHORTENING_KEY_REDIRECT_URL + "?shorteningKey=" + shorteningKeyResponseDto.getShorteningKey()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String deserializeShorteningKeyRequestDto () {

        Gson gson = new GsonBuilder().create();
        return gson.toJson(shorteningKeyRequestDto);
    }

    private ShorteningKeyResponseDto serializeShorteningKey(String shorteningKeyJson) {

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(shorteningKeyJson, ShorteningKeyResponseDto.class);

    }
}
