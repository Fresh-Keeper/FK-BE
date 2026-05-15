package com.freshkeeper.fridge.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Test
    @DisplayName("로그인 후 로그아웃하면 세션이 만료되어야함")
    void logoutTest() throws Exception{
        // 1. 가짜 세션 생성 (로그인 된 상태 가정)
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "testUser");

        // 2. 로그아웃 API 호출
        mockmvc.perform(post("/api/logout")
                .session(session)) // 생성한 세션을 주입
                .andExpect(status().isOk())
                .andExpect(content().string("로그아웃 성공"));

        // 3. 세션 만료되었는지 검증
        assertTrue(session.isInvalid());
    }

}
