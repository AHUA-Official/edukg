package com.example.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements  WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射本地文件夹到URL路径
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/edukg/springboot/src/main/resources/images/");
    }
    // 加
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/**")
                .excludePathPatterns("/files/**");
    }
}