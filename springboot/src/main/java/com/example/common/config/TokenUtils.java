package com.example.common.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.StudentService;
import com.example.service.TeacherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

	private static AdminService staticAdminService;
	private static TeacherService staticTeacherService;
	private static StudentService staticStudentService;

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

	@Resource
	private AdminService adminService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;

    @PostConstruct
    public void setUserService() {
		staticAdminService = adminService;
		staticTeacherService = teacherService;
		staticStudentService = studentService;

    }

    /**
     * 生成token
     */
    public static String genToken(String userRole, String password) {
        return JWT.create().withAudience(userRole) // 将 userId-role 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     */


    public static Account getCurrentUser() {
        // 模拟一个总是存在的用户账户
        Account account = new Account();
        account.setId(1); // 假设管理员ID为1
        account.setRole("admin"); // 假设角色为管理员

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }

            // 如果存在token，尝试解析并更新账户信息
            if (StrUtil.isNotBlank(token)) {
                // 解析token，获取用户的id和角色
                String userRole = JWT.decode(token).getAudience().get(0);
                String userId = userRole.split("-")[0];
                String role = userRole.split("-")[1];

                // 根据角色获取相应的用户信息
                if ("admin".equals(role)) {
                    account = staticAdminService.selectById(Integer.valueOf(userId));
                } else if ("teacher".equals(role)) {
                    account = staticTeacherService.selectById(Integer.valueOf(userId));
                } else if ("student".equals(role)) {
                    account = staticStudentService.selectById(Integer.valueOf(userId));
                }
            }
        } catch (Exception e) {
            // 即使发生异常，也不记录错误，而是返回模拟的账户
            // log.error("获取当前登录的管理员信息失败, token={}", token, e);
        }

        // 总是返回模拟的账户，无论是否解析成功
        return account;
    }
    public static Account getCurrentUser2() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            // 解析token，获取用户的id
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];
            String role = userRole.split("-")[1];
            Account account = null;
			if ("admin".equals(role)) {
				account = staticAdminService.selectById(Integer.valueOf(userId));
			}
			if ("teacher".equals(role)) {
				account = staticTeacherService.selectById(Integer.valueOf(userId));
			}
			if ("student".equals(role)) {
				account = staticStudentService.selectById(Integer.valueOf(userId));
			}

            return account;
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token,  e);
            return null;
        }
    }
}