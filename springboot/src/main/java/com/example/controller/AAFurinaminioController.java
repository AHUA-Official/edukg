package com.example.controller;

import com.example.service.AAFurinaminioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class AAFurinaminioController {
    @Autowired
    private AAFurinaminioService minioDocumentService;

    @GetMapping("/miniodoc/getundoc")
    public ResponseEntity<Map<String, Object>> getUndocumentedDocument(@RequestParam String symbol) {
 return  null;
    }

//    上传文件的接口 传入一个文件   并提供可选的POSTjson参数   symbol   version   chain
@PostMapping("/minio/upload")
public ResponseEntity<Map<String, Object>> uploadFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam(value = "object", required = true) String object,
        @RequestParam(value = "symbol", required = false) String symbol,
        @RequestParam(value = "version", required = false) String version,
        @RequestParam(value = "chain", required = false) String chain,
        @RequestParam(value = "documentuuid", required = false) String documentuuid
) {

    try {
        // 将 MultipartFile 转换为 File
        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);

        // 调用服务层方法，传入 File 对象和其他参数
        Map<String, Object> response = minioDocumentService.uploadFile(tempFile, object, symbol, version, chain,documentuuid);

        // 删除临时文件
        tempFile.delete();

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } catch (IOException e) {
        // 处理异常
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}



}
