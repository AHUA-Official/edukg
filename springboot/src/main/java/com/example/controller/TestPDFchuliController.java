package com.example.controller;

import com.example.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
//接受一个PDF文件的上传     然后把PDF文件存在系统的对应路径里面      之后还需要实现     对PDF做切分   每一页切成2半    然后生成每页的id和名字   依旧是存在系统的对应路径里面
// 最后返回一个json组      告诉我们生成的切分出来的图片的请求路径    页数_1: url    页数_2 : url

@RestController
public class TestPDFchuliController {

    @Autowired
    private PDFService testPDFService;




    @PostMapping("/upload")
    public ResponseEntity<String> uploadPDF(@RequestParam("file") MultipartFile file) {
        // 调用服务处理上传的PDF文件
        String response = testPDFService.uploadAndRegisterPDF(file);
        return ResponseEntity.ok(response);
    }

    // 新增接口，根据itemId查询并处理PDF图片URL列表
    @GetMapping("/seepdfpnglist/{itemId}")
    public ResponseEntity<List<Map<String, String>>> seePdfPngListByItemId(@PathVariable Integer itemId) {
        try {
            // 假设调用数据库逻辑获取PDF的实际文件名
            String pdfActualFileName = testPDFService.selectById(itemId).getStoredName();
            if (pdfActualFileName == null) {
                return ResponseEntity.notFound().build();
            }

            // 使用获取到的PDF实际名称调用处理方法
            List<Map<String, String>> imageUrlList = testPDFService.handlePdfAndGenerateUrls(pdfActualFileName);
            if (imageUrlList != null && !imageUrlList.isEmpty()) {
                return ResponseEntity.ok(imageUrlList);
            } else {
                // 这里假设如果处理过程中出现问题，返回空列表或错误信息
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (Exception e) {
            // 处理可能出现的异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/pdf" )
    public ResponseEntity<String> Yourway  (@PathVariable Long itemId) {

        String  response = "笨蛋 ,接口未完成好吧";

        return  ResponseEntity.ok(response);
    }
}