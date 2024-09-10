package com.example.controller;


//要求    只在Controller里面构建示例的json响应嗷    不要导入什么Service   什么 Entity  和什么的
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class isOKController {



    @GetMapping(value = "/isOK")
    public ResponseEntity<Map<String, String>> isOK() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }






}


