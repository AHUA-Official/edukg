package com.example.controller;


//要求    只在Controller里面构建示例的json响应嗷    不要导入什么Service   什么 Entity  和什么的
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
public class AAFurinaLoginController {

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


    @PostMapping(value = "/edukglogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();
        String role = credentials.get("role");
        String jobNum = credentials.get("job_num");
        String password = credentials.get("password");

        Account account = new Account();
        account.setName(role);
        account.setPassword(password);
        Map<String, Object> data = new LinkedHashMap<>();
        String token = null;


        if ("student".equals(role)) {
            // studentService;
            Student student1 = new Student();
            student1.setUsername(jobNum);
            System.out.println(student1.toString());
            Student student = studentService.selectByUsername(jobNum);

            if (!password.toString().equals(student.getPassword().toString())) {
                System.out.println("!studentService.login2(account)");
                response.put("status", "failure");
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {

                data.put("userId", student.getId());
                String  studentid  =student.getId().toString();
                data.put("username", student.getUsername());
                data.put("email", student.getEmail());
                data.put("role", role);
                data.put("avatar", student.getAvatar());

                data.put("updatedAt", student.getUpdatedAt());
                data.put("createdAt", student.getCreateAt());
                response.put("data", data);
                response.put("status", "success");
                token = generateJwtToken(jobNum,role ,studentid);
            }
        } else if ("teacher".equals(role)) {
            //teacherService;
            Teacher teacher = teacherService.selectByUsername(jobNum);
            if (!password.toString().equals(teacher.getPassword().toString())) {
                System.out.println("!teachertService.login2(account)");
                response.put("status", "failure");
                response.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                data.put("userId", teacher.getId());
                String  teacherid  =teacher.getId().toString();
                data.put("username", teacher.getUsername());
                data.put("email", teacher.getEmail());
                data.put("role", role);
                data.put("avatar", teacher.getAvatar());
                data.put("updatedAt", teacher.getUpdatedAt());
                data.put("createdAt", teacher.getCreatedAt());
                response.put("data", data);
                response.put("status", "success");
                token = generateJwtToken(jobNum,role ,teacherid);
                //return ResponseEntity.status(HttpStatus.OK).body(response);

            }
        } else {

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        // 创建一个HttpHeaders实例并设置Authorization头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        //headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, PATCH, HEAD, OPTIONS, TRACE");
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        headers.set("Access-Control-Expose-Headers", "Authorization");
        response.put("data", data);
        response.put("status", "success");
        return ResponseEntity.ok().headers(headers).body(response);
    }
    private String generateJwtToken(String jobNum, String role, String userid) {
        Algorithm algorithm = Algorithm.HMAC256("secretKey".getBytes());
        long now = System.currentTimeMillis();
        String token = JWT.create()
                .withClaim("jobNum", jobNum)
                .withClaim("role",role)
                .withClaim("userid", userid)
                .withIssuedAt(Calendar.getInstance().getTime())
                .withExpiresAt(new Date(now + 360000000)) // 1小时后过期
                .sign(algorithm);
        return token;
    }
//一个查询接口   啥也不带   根据Header里面的Authorization头    返回里面返回的信息
@GetMapping("/querylogin")
public ResponseEntity<Map<String, Object>> getUserInfo(@RequestHeader("Authorization") String authorization) {
    Map<String, Object> response = new HashMap<>();
    try {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.put("status", "failure");
            response.put("message", "Missing or invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = authorization.substring(7);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secretKey".getBytes())).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        String jobNum = decodedJWT.getClaim("jobNum").asString();
        String role = decodedJWT.getClaim("role").asString();
        String userid = decodedJWT.getClaim("userid").asString();

        Map<String, Object> data = new HashMap<>();
        data.put("jobNum", jobNum);
        data.put("role", role);
        data.put("userid", userid);

        response.put("data", data);
        response.put("status", "success");
        return ResponseEntity.ok(response);
    } catch (JWTVerificationException exception) {
        response.put("status", "failure");
        response.put("message", "Invalid or expired token");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
}