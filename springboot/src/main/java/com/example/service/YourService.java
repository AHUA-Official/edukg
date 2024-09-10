package com.example.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class YourService {

    /**

     */
    public String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        return String.format("%06d", random.nextInt(1_000_000)); // 生成000000到999999之间的随机数
    }

    /**

     */
    public String YourServiceImpl() {

        return "服务成功调用";
    }}