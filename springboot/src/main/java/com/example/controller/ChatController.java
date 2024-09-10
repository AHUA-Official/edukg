package com.example.controller;

import com.example.entity.Chat;
import com.example.service.BigModelNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private BigModelNewService bigModelNewService;
    @CrossOrigin(origins = "*", allowedHeaders = "*") // 允许所有源和所有头
    @PostMapping("/chat")
    public String chatWithBigModel(@RequestBody String qusestion) {
        try {
            //question，用于存放用户的提问
            String question = qusestion;
            if (question == null || question.trim().isEmpty()) {
                return "提问不能为空";
            }
            // 调用BigModelNewService的chat方法与大模型交互
            String answer = BigModelNewService.chat(question);
            return answer;
        } catch (Exception e) {
            // 记录日志或处理异常
            e.printStackTrace();
            return "出现错误，请稍后再试";
        }
    }
}