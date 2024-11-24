package com.example;

import com.example.controller.AAFurinaayeyouOKController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
public class isOKTest {

    @Autowired
    private AAFurinaayeyouOKController doTestController;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(doTestController).build(); // 初始化MockMvc
    }

    @Test
    public void getTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/areyouok")).andReturn();
        // 这里可以添加断言来验证结果，例如：
        // assertEquals(200, mvcResult.getResponse().getStatus());

        int status = mvcResult.getResponse().getStatus();
        System.out.println("HTTP Status: " + status);

        // 获取响应内容（假设响应体是json）
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("Response Content: " + content);
    }
}