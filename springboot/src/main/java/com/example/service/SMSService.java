package com.example.service;

import com.example.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SMSService {

    private static final String HOST = "https://cxkjsms.market.alicloudapi.com";
    private static final String PATH = "/chuangxinsms/dxjk";
    private static final String METHOD = "POST";
    private static final String APP_CODE = "df4fbb3124154b538de64cac18ae5a0c";
    public static final ThreadLocal<Map<String, String>> smsVerificationCodeHolder =
            ThreadLocal.withInitial(ConcurrentHashMap::new);
    public String sendVerificationCode(String mobile) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + APP_CODE);

        Map<String, String> querys = new HashMap<>();


// 在sendSms方法内部
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            // 生成0-9之间的随机数
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        String verificationCode = sb.toString();
        querys.put("content", "【芙芙】验证码:" + verificationCode + "（10分钟内有效）。您正在进行身份验证，请勿将验证码告诉他人哦。");
        //querys.put("content", "【芙芙】验证码:123456（10分钟内有效）。您正在进行身份验证，请勿将验证码告诉他人哦。");
        querys.put("mobile", mobile);
        System.out.println(querys);
        Map<String, String> bodys = new HashMap<>();

        try {
            HttpResponse response = HttpUtils.doPost(HOST, PATH, METHOD, headers, querys, bodys);
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("Response Body: " + responseBody);
            if (response.getStatusLine().getStatusCode() == 200) {
                //
                Map<String, String> smsCache = smsVerificationCodeHolder.get();
                smsCache.put(mobile,verificationCode); // 假设验证码是结果字符串的一部分
                smsVerificationCodeHolder.set(smsCache);
//                Map<String, String> smsCache = smsVerificationCodeHolder.get();
//                return smsCache.get(phone);
                System.out.println(smsCache.toString());

                return "短信发送成功";
            } else {
                return "短信发送失败，响应码：" + response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            return "短信发送失败: " + e.getMessage();
        }
    }
}