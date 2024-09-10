package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.YourService;

@RestController
public class YourController {

    @Autowired
    private YourService yourService;

    @GetMapping("/you")
    public ResponseEntity<String> Yourway  (@PathVariable Long itemId) {

      String  response = yourService.YourServiceImpl();

        return  ResponseEntity.ok(response);
    }
}