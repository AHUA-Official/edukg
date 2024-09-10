package com.example.common.config;

import com.example.entity.Chat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class AppConfig {
    @Bean
    public Chat chatBean() {
        // 根据需要初始化Chat对象，例如从数据库获取数据等
        //
        return new Chat("i"+"", false, new ArrayList<>(), new ArrayList<>());
    }
}
