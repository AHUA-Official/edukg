package com.example.controller;

import com.example.service.AAFurinaminioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public class AAFurinaminioController {
    @Autowired
    private AAFurinaminioService minioDocumentService;

    @GetMapping("/miniodoc/getundoc")
    public ResponseEntity<Map<String, Object>> getUndocumentedDocument(@RequestParam String symbol) {
        Map<String, Object> documentInfo = minioDocumentService.findDocumentToParseBySymbol(symbol);
        if (documentInfo == null || documentInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(documentInfo);
    }


}
