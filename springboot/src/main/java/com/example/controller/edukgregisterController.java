package com.example.controller;

import com.example.entity.RegisterRequest;
import com.example.entity.UserInfo;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.example.service.SMSService.smsVerificationCodeHolder;

@RestController
public class edukgregisterController {
    @Resource
    StudentService studentService;
    @Resource
    TeacherService teacherService;
    @PostMapping(value = "/register0720", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>>register (@RequestBody RegisterRequest request) {
            // 直接访问实体类属性
            String role = request.getRole();
            String userJubNum = request.getUser_jub_num();
            //UserInfo userinfo = request.getUserInfo();
            String verification_code          = request.getVerification_code();
            String user_name = request.getUser_name();
            String user_phone = request.getUser_phone();
            String user_email = request.getUser_email();
        System.out.println(role);
        //System.out.println(userinfo.toString());
        System.out.println(request.toString());


//伪响应   只要不是  role 不是teacher不是student   然后user_jub_num是2022090917008就报错
        // 检查用户名和密码是否正确
       // boolean isValid ="teacher".equals(role) || "student".equals(role) || ! "2022090917008".equals(userJubNum);
        String role1 = "teacher"; // 示例角色
        String userJubNum1 = "2022090917008"; // 示例用户编号

        boolean isValidRole = "teacher".equals(role) || "student".equals(role);
        boolean isValidUserJubNum = true;
             //   //userJubNum != null && userJubNum.matches("^[0-9]{13}$"); // 假设用户编号应该是13位数字

        Map<String, String> smsCache = smsVerificationCodeHolder.get();
        //Map<String, String> smsCache = smsVerificationCodeHolder.get();
             String verycode =smsCache.get(user_phone);

        boolean isNotSpecificUser =verycode.equals(verification_code);

        boolean isValid = isValidRole && isValidUserJubNum && isNotSpecificUser;
        // 根据验证结果构建响应
if (isValid){
    if (role.equals("teacher")){
        Teacher teacher = new Teacher();
        teacher.setName(user_name);
        teacher.setPhone(user_phone);
        teacher.setEmail(user_email);
        // 调用服务层方法添加教师信息
        teacherService.add(teacher);


    }else if (role.equals("student")){
      Student student = new Student();
       student.setName(user_name);
       student.setPhone(user_phone);
       student.setEmail(user_email);
       studentService.add(student);
    }
}else
        {

        }
        return buildResponse(isValid);
    }




    private ResponseEntity<Map<String, Object>> buildResponse(boolean isValid) {
        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            response.put("status", "success");
            //Map<String, String> data = new HashMap<>();
            //data.put("token", "generated_token");
            //response.put("data", data);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }
    }
}

