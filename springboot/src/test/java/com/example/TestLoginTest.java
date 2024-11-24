package com.example;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.controller.SMSController;
import com.example.service.SMSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
@WebMvcTest
public class TestLoginTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SMSService smsService;

    private Map<String, String> requestBody;

    @BeforeEach
    public void setUp() {
        requestBody = new HashMap<>();
        requestBody.put("phone", "15282149533");
    }




    @Test
    public void testSendSmsSuccess() throws Exception {

        // 发送 POST 请求并验证响应
        mockMvc.perform(post("/sendSmsPASTUSELESS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ok").value(true));
    }


    @Test
    public void testSendSmsFailure() throws Exception {
        // 模拟服务层返回失败的消息

        // 发送 POST 请求并验证响应
        mockMvc.perform(post("/sendSmsPASTUSELESS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ok").value(false));

    }
}
