package com.example.controller;
///df4fbb3124154b538de64cac18ae5a0c
import com.example.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
//【芙芙】  验证码：＊＊＊＊＊＊（10分钟内有效）。您正在进行身份验证，请勿将验证码告诉他人哦。
@RestController
public class SMSPastController {
//给我发的post消息是   {"phone"  :   phone字段
    //我的回应是    {"ok" :  "true"}
    @PostMapping("/sendSmsPASTUSELESSAAA")
    public ResponseEntity<String> sendSms() {

        String host = "https://cxkjsms.market.alicloudapi.com";
        String path = "/chuangxinsms/dxjk";
        String method = "POST";
        String appcode = "df4fbb3124154b538de64cac18ae5a0c";//开通服务后 买家中心-查看AppCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【芙芙】验证码:123456（10分钟内有效）。您正在进行身份验证，请勿将验证码告诉他人哦。");
//17882006836
        //15282149533

        querys.put("mobile", "15282149533");
        Map<String, String> bodys = new HashMap<String, String>();
        System.out.println(querys);
        System.out.println(querys);






        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response);
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("Response Body: " + responseBody);
            return ResponseEntity.ok("短信发送成功，响应状态码: " + response.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("短信发送失败: " + e.getMessage());
        }
    }
}