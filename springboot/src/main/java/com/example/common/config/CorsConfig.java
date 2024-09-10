package com.example.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        List<String> allowedMethods = Arrays.asList("GET", "POST", "PUT");
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setMaxAge(360000L); // 设置预检请求的有效期为3600秒

        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
//Access-Control-Allow-Origin：允许访问的源（Origin）
//        Access-Control-Allow-Methods：允许实际请求的Http方法们
//        Access-Control-Allow-Headers：允许实际请求的请求头们