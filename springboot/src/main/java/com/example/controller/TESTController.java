package com.example.controller;

import com.example.common.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@RestController
public class TESTController {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GetMapping(value = "/stream-endpoint", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamFixedText() {
        final SseEmitter emitter = new SseEmitter();
        executorService.submit(() -> {
            try {
                while (true) {
                    // 发送固定文本内容
                    emitter.send("Fixed text content\n");
                    // 等待一段时间，例如1秒
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                // 处理异常
                e.printStackTrace();
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }





}
