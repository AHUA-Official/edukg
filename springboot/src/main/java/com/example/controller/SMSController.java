package com.example.controller;

// 导入所需的包...

import com.example.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SMSController {

    @Autowired
    private SMSService smsService;

    @PostMapping("/sendSmsPASTUSELESS")
    public ResponseEntity<Map<String, Boolean>> sendSms(@RequestBody Map<String, String> requestBody) {
        //15282149533
        String mobile  =requestBody.get("phone");
        String result = smsService.sendVerificationCode(mobile);
       // ObjectMapper mapper = new ObjectMapper(); // 创建一个ObjectMapper实例
//        if (result.startsWith("短信发送成功")) {
//            return ResponseEntity.ok(result);//响应体你里面加个{OK：true}
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);//响应体你里面加个{ok：false}
//        }
//    }

        if (result.startsWith("短信发送成功")) {
            // 如果短信发送成功，返回一个带有OK标志的JSON对象
            Map<String, Boolean> response = new HashMap<>();
            response.put("ok", true);
            return ResponseEntity.ok().body(response);
        } else {
            // 如果短信发送失败，返回一个带有错误标志的JSON对象
            Map<String, Boolean> response = new HashMap<>();
            response.put("ok", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }}