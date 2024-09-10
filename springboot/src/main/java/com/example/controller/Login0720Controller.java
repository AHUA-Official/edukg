package com.example.controller;


//要求    只在Controller里面构建示例的json响应嗷    不要导入什么Service   什么 Entity  和什么的
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class Login0720Controller {

    @Resource
    StudentService studentService;
    @Resource
     TeacherService teacherService;

    @CrossOrigin(
            origins = "*",
            allowedHeaders = "*",
            methods = {
                    RequestMethod.GET,
                    RequestMethod.POST,
                    RequestMethod.PUT,
                    RequestMethod.PATCH,
                    RequestMethod.HEAD,
                    RequestMethod.OPTIONS
            }
    )



    @PostMapping(value = "/edukglogin0720", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();
        String role = credentials.get("role");
        String jobNum = credentials.get("job_num");
        String password = credentials.get("password");

        Account account = new  Account();
        account.setName(role);
        account.setPassword(password);
        Map<String, Object> data = new LinkedHashMap<>();


        if ("student".equals(role)) {
            // studentService;
            Student student1 =new Student();
            student1.setUsername(jobNum);
            System.out.println(student1.toString());
            Student student = studentService.selectByUsername(jobNum);
            System.out.println("NINNINIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
            System.out.println(student.toString());
            System.out.println(student1.toString());
            System.out.println("NINNINIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
//        "data": {
//        "userId": "123456",
//        "username": "admin",
//        "email": "admin@example.com",
//        "role": "teacher",
//        "avatar": "http://example.com/path/to/avatar.jpg",
//        "updatedAt": "2024-07-20T12:34:56Z",
//        "createdAt": "2024-01-01T00:00:00Z"
            if (!password.toString().equals(student.getPassword().toString())) {
                System.out.println("!studentService.login2(account)");
                response.put("status", "failure");
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }else {
                data.put("userId", student.getId());
                data.put("username",student.getUsername());
                data.put("email", student.getEmail());
                data.put("role", role);
                data.put("avatar",student.getAvatar());

                data.put("updatedAt", student.getUpdatedAt());
                data.put("createdAt", student.getCreateAt());
                response.put("data", data);
                response.put("status", "success");
            }
        } else if ("teacher".equals(role)) {
           //teacherService;
            Teacher teacher = teacherService.selectByUsername(jobNum);
            if (!password.toString().equals(teacher.getPassword().toString())) {
                System.out.println("!teachertService.login2(account)");
                response.put("status", "failure");
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }else {
                data.put("userId", teacher.getId());
                data.put("username",teacher.getUsername());
                data.put("email", teacher.getEmail());
                data.put("role", role);
                data.put("avatar",teacher.getAvatar());

                data.put("updatedAt", teacher.getUpdatedAt());
                data.put("createdAt", teacher.getCreatedAt());
                response.put("data", data);
                response.put("status", "success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        } else {

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }




        String token = generateJwtToken(jobNum);
        // 创建一个HttpHeaders实例并设置Authorization头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        //headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, PATCH, HEAD, OPTIONS, TRACE");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");



        response.put("data", data);
        response.put("status", "success");

        return ResponseEntity.ok().headers(headers).body(response);
    }

    //Access-Control-Allow-Origin：允许访问的源（Origin）
//        Access-Control-Allow-Methods：允许实际请求的Http方法们
//        Access-Control-Allow-Headers：允许实际请求的请求头们
    private String generateJwtToken(String jobNum) {
        Algorithm algorithm = Algorithm.HMAC256("secretKey".getBytes());
        long now = System.currentTimeMillis();
        String token = JWT.create()
                .withClaim("jobNum", jobNum)
                .withIssuedAt(Calendar.getInstance().getTime())
                .withExpiresAt(new Date(now + 360000000)) // 1小时后过期
                .sign(algorithm);
        return token;
    }

    @PostMapping(value = "/edukglogin072000", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String, Object>> login000(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();
        String  role = credentials.get("role");
        if (!"2022090917008".equals(credentials.get("job_num")) || !"password".equals(credentials.get("password"))) {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        String jobNum = credentials.get("job_num");
        String token = generateJwtToken(jobNum);

        // 创建一个HttpHeaders实例并设置Authorization头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        //headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, PATCH, HEAD, OPTIONS, TRACE");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("userId", "123456");
        data.put("username", "admin");
        data.put("email", "admin@example.com");
        data.put("role", role);
        data.put("avatar", "http://example.com/path/to/avatar.jpg");

        data.put("updatedAt", "2024-07-20T12:34:56Z");
        data.put("createdAt", "2024-01-01T00:00:00Z");
        response.put("data", data);
        response.put("status", "success");

        return ResponseEntity.ok().headers(headers).body(response);
    }


    @GetMapping("/s/{username}")
    public ResponseEntity<?> getStudentByUsername(@PathVariable String username) {
        Student student = studentService.selectByUsername(username);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> studentInfo = new HashMap<>();
        studentInfo.put("userId", student.getId());
        studentInfo.put("username", student.getUsername());
        studentInfo.put("name", student.getName());
        studentInfo.put("avatar", student.getAvatar());
        studentInfo.put("phone", student.getPhone());
        studentInfo.put("email", student.getEmail());
        studentInfo.put("updatedAt", student.getUpdatedAt());
        studentInfo.put("createdAt", student.getCreateAt());
        studentInfo.put("deleted", student.getDeleted());
        studentInfo.put("logintype", student.getLogintype());
        studentInfo.put("major", student.getMajor());
        System.out.println(studentInfo.toString());
        return ResponseEntity.ok(studentInfo);
    }


}



//这个是假后端  现在   使用真实的数据库交互 进行登入登出
//{
//        "role": "teacher",
//        "job_num": "2022090917008",
//        "password": "password"
//
//        }
//根据role是teacher还是student去查询对应的学生表和课程表
//jobnum对应的是学生表的username  教师表的user_jub_name
//然后看password对不对




//返回的是
//{

//        },
//        "status": "success"
//        }

