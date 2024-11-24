package com.example.controller;

import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AAFurinaayeyouOKController {

    @GetMapping(value = "/areyouok")
    public ResponseEntity<Map<String, Object>> areyouok(){

        Map<String, Object> response = new HashMap<>();



        response.put("message", "我感觉很好");


        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/")
    public ResponseEntity<Map<String, Object>> imfine(){

        Map<String, Object> response = new HashMap<>();



        response.put("message", "我想耍朋友");


        return ResponseEntity.ok(response);
    }

}
