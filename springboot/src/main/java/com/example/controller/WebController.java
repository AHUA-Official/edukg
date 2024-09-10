package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.AdminService;

import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  描述：系统用户操作相关接口
 */
@RestController
public class WebController {

	@Resource
	private AdminService adminService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;


    /**
     * 描述：用户登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account loginAccount = null;
		if ("admin".equals(account.getRole())) {
			loginAccount = adminService.login(account);
		}
		if ("teacher".equals(account.getRole())) {
			loginAccount = teacherService.login(account);
		}
		if ("student".equals(account.getRole())) {
			loginAccount = studentService.login(account);
		}

        return Result.success(loginAccount);
    }

    /**
     * 描述：用户注册接口
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
		if ("admin".equals(account.getRole())) {
			adminService.register(account);
		}
		if ("teacher".equals(account.getRole())) {
			teacherService.register(account);
		}
		if ("student".equals(account.getRole())) {
			studentService.register(account);
		}

        return Result.success();
    }

    /**
    * 描述：更新密码接口
    */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
		if ("admin".equals(account.getRole())) {
			adminService.updatePassword(account);
		}
		if ("teacher".equals(account.getRole())) {
			teacherService.updatePassword(account);
		}
		if ("student".equals(account.getRole())) {
			studentService.updatePassword(account);
		}

        return Result.success();
    }
}
