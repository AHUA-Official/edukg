package com.example.common.config;
import  io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class minioConfig {
    @Bean
    public  MinioClient    mioioClient(){
        return MinioClient.builder()
                .endpoint("http://49.234.47.133:9000")
                .credentials("minioadmin", "minioadmin")
                .build();


    }
}
