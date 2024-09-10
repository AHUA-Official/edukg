
//登录
//
//        方法：POST
//        Url：/edukg/login
//        请求：
//        json
//        {
//        "username": "user1",
//        "password": "password123"
//        }
//json
//        {
//        "status": "success",
//        "data": {
//        "token": "generated_token"
//        }
//        }
////        失败   返回失败和错误原因
//用于给前端测试联调  不需要Service啥的   直接返回示例的json响应即可   比对的数据是  admin  password
package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/edukg")
public class EdukgLoginController {

    @PostMapping(value = "/edukglogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> requestBody) {
        // 预设的用户名和密码
        String correctUsername = "admin";
        String correctPassword = "password";

        // 从请求体中获取用户名和密码
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        // 检查用户名和密码是否正确
        boolean isValid = correctUsername.equals(username) && correctPassword.equals(password);

        // 根据验证结果构建响应
        return buildResponse(isValid, username, password);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(boolean isValid, String username, String password) {
        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            response.put("status", "success");
            Map<String, String> data = new HashMap<>();
            data.put("token", "generated_token");
            response.put("data", data);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }
    }
}