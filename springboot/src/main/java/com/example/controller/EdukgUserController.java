package com.example.controller;
//教师用户表(展示)tbl_teacher
//        - id (integer, 主键): 用户唯一标识。
//        - user_name (varchar): 用户昵称，用于前端展示。
//        - user_job_num (varchar):  用户工号  用于前端展示
//        - user_email(varchar): 用户邮箱   用于前端展示
//        - user_phone(varchar):用户电话联系方式,用于前端展示
//        - Password   (varchar)  :  加密后的密码
//        - avatar_url (varchar): 用户头像URL，同样面向前端展示。
//        - login_type(integar):    可用的登录方式
//        - deleted(integar):    是否删除
//        - created_at (datetime): 用户账号创建时间。
//        - updated_at (datetime): 用户信息最后更新时间。
//        教师用户表(登录鉴权用)tbl_teacher_auth
//        - id (integer, 主键): 认证信息唯一标识。
//        - teacher_id (integer, foreign key): 引用teachers_profile表的id，建立与用户信息的关联。
//        - authentication_type (varchar): 认证类型，如'email', 'phone', 'username',"user_job_num, 或者第三方服务如'Wechat', 'Weibo'等。
//        - identifier (varchar): 认证标识符，如邮箱地址、手机号、用户名或第三方平台的唯一ID。
//        - credential (varchar): 凭证信息，站内密码采用哈希存储，第三方登录则存储access_token等安全令牌。
//        - is_active (boolean, default true): 标记该认证方式是否有效。
//        - created_at (datetime): 认证信息创建时间。
//        - last_login_at (datetime): 最后一次登录时间。
//        - extra_data (json, optional): 扩展字段，存储与认证方式相关的额外数据，例如登录IP、设备信息等。
//        学生信息展示表 (tbl_student)
//        - StudentID (integer, 主键): 学生唯一标识。
//        - UserName (varchar, unique): 学生的登录用户名，确保唯一性。
//        - DisplayName (varchar, optional): 可选，学生显示名称，可能用于更友好展示。
//        - AvatarURL (varchar): 学生头像URL，面向前端展示。
//        - FullName (varchar): 学生的全名。
//        - login_type(integar):    可用的登录方式
//        - Email (varchar, unique): 学生的电子邮箱地址，用于通讯。
//        - Phone: 学生电话联系方式  用于通讯
//        - Stu_num (varchar): 学生学号。
//        - Major (varchar): 学生所学专业。
//        - deleted (boolean, default false): 标记学生信息是否被逻辑删除。
//        - CreatedAt (datetime): 学生账号创建时间。
//        - UpdatedAt (datetime): 学生信息最后更新时间。
//        学生用户鉴权表 (tbl_student_auth)
//        - AuthID (integer, 主键): 认证信息唯一标识。
//        - StudentID (integer, foreign key): 引用tbl_student_profile表的StudentID，建立与学生信息的关联。
//        - AuthenticationType (varchar): 认证类型，如'email', 'studentID'等。
//        - Identifier (varchar): 认证标识符，如邮箱地址或学号。
//        - CredentialHash (varchar): 凭证信息的哈希形式，如密码的哈希值。
//        - IsActive (boolean, default true): 标记该认证方式是否有效。
//        - CreatedAt (datetime): 认证信息创建时间。
//        - LastLoginAt (datetime): 最后一次登录时间。
//        - ExtraData (json, optional): 扩展字段，存储与认证相关的额外数据，如登录IP、设备信息等。
//
//更据这些字段     选取必选数据  越少越好    写关于用户基础管理的Controller    根据json数据里面的字段是这样的   data里面是关于用户的具体信息  数据
//比对数据 教师    标识字段  teacher   data里面是必须有比对数据  老师工号   334455     比对成功  增加不可以  删除可以  查询可以  修改可以
//        比对数据 学生    标识字段  teacher   data里面是必须有比对数据  学生写号   34238793  比对成功  增加不可以  删除可以  查询可以  修改可以
//////        失败   返回失败和错误原因
//用于给前端测试联调  不需要Service啥的   直接返回示例的json响应即可   比

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/edukgusers")
public class EdukgUserController {


    @GetMapping("/{type}")
    public ResponseEntity<?> getUser(@PathVariable String type, @RequestParam Map<String, String> params) {
        if ("teacher".equals(type)) {
            String jobNum = params.get("job_num");
            if (jobNum != null && jobNum.equals("123456")) {
                // 模拟教师数据
                Teacher teacher = new Teacher("Mr. Smith", "123456");
                return ResponseEntity.ok(teacher);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else if ("student".equals(type)) {
            String stuNum = params.get("stu_num");
            if (stuNum != null && stuNum.equals("789012")) {
                // 模拟学生数据
                Student student = new Student("John Doe", "789012");
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().body("Invalid request");
    }

    @DeleteMapping("/{type}")
    public ResponseEntity<?> deleteUser(@PathVariable String type, @RequestParam Map<String, String> params) {
        // 模拟删除操作
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{type}")
    public ResponseEntity<?> updateUser(@PathVariable String type, @RequestBody Map<String, Object> data) {
        // 模拟更新操作
        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("/{type}")
    public ResponseEntity<?> addUser(@PathVariable String type, @RequestBody Map<String, Object> data) {
        if ("teacher".equals(type)) {
            if (data.containsKey("name") && data.containsKey("jobNumber")) {
                Teacher teacher = new Teacher((String) data.get("name"), (String) data.get("jobNumber"));
                return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
            } else {
                return ResponseEntity.badRequest().body("Missing required fields");
            }
        } else if ("student".equals(type)) {
            if (data.containsKey("name") && data.containsKey("studentNumber")) {
                Student student = new Student((String) data.get("name"), (String) data.get("studentNumber"));
                return ResponseEntity.status(HttpStatus.CREATED).body(student);
            } else {
                return ResponseEntity.badRequest().body("Missing required fields");
            }
        }
        return ResponseEntity.badRequest().body("Invalid request");
    }
//
//    @GetMapping("/{type}")
//    public ApiResponse<?> getUser(@PathVariable String type, @RequestParam Map<String, String> params) {
//        if ("teacher".equals(type)) {
//            String jobNum = params.get("job_num");
//            if (jobNum != null && jobNum.equals("123456")) {
//                // 模拟教师数据
//                return new ApiResponse<>(200, "Success", new Teacher("Mr. Smith", "123456"));
//            } else {
//                return new ApiResponse<>(404, "Teacher not found", null);
//            }
//        } else if ("student".equals(type)) {
//            String stuNum = params.get("stu_num");
//            if (stuNum != null && stuNum.equals("789012")) {
//                // 模拟学生数据
//                return new ApiResponse<>(200, "Success", new Student("John Doe", "789012"));
//            } else {
//                return new ApiResponse<>(404, "Student not found", null);
//            }
//        }
//        return new ApiResponse<>(400, "Invalid request", null);
//    }
//
//    @DeleteMapping("/{type}")
//    public ApiResponse<?> deleteUser(@PathVariable String type, @RequestParam Map<String, String> params) {
//        // 模拟删除操作
//        return new ApiResponse<>(200, "User deleted successfully", null);
//    }
//
//    @PutMapping("/{type}")
//    public ApiResponse<?> updateUser(@PathVariable String type, @RequestBody Map<String, Object> data) {
//        // 模拟更新操作
//        return new ApiResponse<>(200, "User updated successfully", null);
//    }
//    @PostMapping("/{type}")
//    public ApiResponse<?> addUser(@PathVariable String type, @RequestBody Map<String, Object> data) {
//        if ("teacher".equals(type)) {
//            // 检查data是否包含必要的字段
//            if (data.containsKey("name") && data.containsKey("jobNumber")) {
//                // 创建一个新的教师对象
//                Teacher teacher = new Teacher((String) data.get("name"), (String) data.get("jobNumber"));
//                // 这里你可以将teacher对象保存到数据库或其他持久化存储中
//                // 模拟添加操作成功
//                return new ApiResponse<>(201, "Teacher added successfully", teacher);
//            } else {
//                return new ApiResponse<>(400, "Missing required fields", null);
//            }
//        } else if ("student".equals(type)) {
//            // 检查data是否包含必要的字段
//            if (data.containsKey("name") && data.containsKey("studentNumber")) {
//                // 创建一个新的学生对象
//                Student student = new Student((String) data.get("name"), (String) data.get("studentNumber"));
//                // 这里你可以将student对象保存到数据库或其他持久化存储中
//                // 模拟添加操作成功
//                return new ApiResponse<>(201, "Student added successfully", student);
//            } else {
//                return new ApiResponse<>(400, "Missing required fields", null);
//            }
//        }
//        return new ApiResponse<>(400, "Invalid request", null);
//    }
}

class ApiResponse<T> {
    private int status;
    private String reason;
    private T data;

    public ApiResponse(int status, String reason, T data) {
        this.status = status;
        this.reason = reason;
        this.data = data;
    }

    // 省略getter和setter方法
}

class Teacher extends com.example.entity.Teacher {
    private String name;
    private String jobNumber;

    public Teacher(String name, String jobNumber) {
        this.name = name;
        this.jobNumber = jobNumber;
    }

    public Teacher() {

    }

    // 省略getter和setter方法
}

class Student extends com.example.entity.Student {
    private String name;
    private String studentNumber;

    public Student(String name, String studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    public Student() {

    }

    // 省略getter和setter方法
}
