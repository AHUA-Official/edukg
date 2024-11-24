package com.example.controller;

import com.example.entity.AAFurinaminiodocuments;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AAFurinaminioController {
    @Autowired
    private AAFurinaminioService minioDocumentService;

    @GetMapping("/minio/getundoc")
    public ResponseEntity<Map<String, Object>> getUndocumentedDocument(@RequestParam(required = false, defaultValue = "1") int limi) {

        List<AAFurinaminiodocuments> result =minioDocumentService.updateNeedParseToWaiting(1);

            Map<String, Object> response = new HashMap<>();
            if (!result.isEmpty()) {
                response.put("data",result);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
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

        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);

        Map<String, Object> response = minioDocumentService.uploadFile(tempFile, object, symbol, version, chain,documentuuid);

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
    @PostMapping("/minio/uploadjson")
    public ResponseEntity<Map<String, Object>> uploadjsonFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "object", required = true) String object,
            @RequestParam(value = "symbol", required = false) String symbol,
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "chain", required = false) String chain,
            @RequestParam(value = "documentuuid", required = true) String documentuuid
    ) {

        try {
            String myobject ="parse_json";
            String mysymbol = (symbol != null && !symbol.isEmpty()) ? symbol : "furina";
            String myversion = (version != null && !version.isEmpty()) ?version: "1";
            String mychain = (chain != null && !chain.isEmpty()) ?  chain: "1";


            File tempFile = File.createTempFile("upload", file.getOriginalFilename());
            file.transferTo(tempFile);

            Map<String, Object> response = minioDocumentService.uploadFile(tempFile, myobject, mysymbol, myversion, mychain,documentuuid);
            System.out.println("传参参参");
            System.out.println(myobject);
            System.out.println("myobject: " + myobject);
            System.out.println("mysymbol: " + mysymbol);
            System.out.println("myversion: " + myversion);
            System.out.println("mychain: " + mychain);
            System.out.println("documentuuid: " + documentuuid);
            System.out.println("respose"+response);

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
