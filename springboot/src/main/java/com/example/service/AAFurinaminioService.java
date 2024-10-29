package com.example.service;

import io.minio.MinioClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
@Service
public class AAFurinaminioService {
    @Resource
    private MinioClient minioClient;

    public void testMinIOClient() {
        System.out.println(minioClient);


    }
}
