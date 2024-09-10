package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.entity.Bookcompnet;
import com.example.entity.Booknext;
import com.example.entity.Entity;
import com.example.entity.Triple;
import com.example.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
@RestController
@RequestMapping("/compnet")
public class FurinabookpinglunController {

    @Resource
    private com.example.service.TripleService TripleService;
    @Resource
    private com.example.service.EntityService  EntityService;
    @Resource
    private BooknextService booknextService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private BookcompnetService bookcompnetService;

    @PostMapping("/addcompnets")
    public Result add1(@RequestBody Map<String, String> jsonMap
                     ) {
// bookid     user的role  和user的id 还有评论的详情
        //根据   role选择查询到表是teacherService还是什么    然后获得用户的名字  存入表里面   需要写一个默认的初始化函数
        //实体的字段设计
        //   /** ID */
        //    private Integer id;
        //    /** 书本id */
        //    private Integer bookid;
        //    /** 角色 是student就去student */
        //    private String role;
        //    /** 角色在各自表里面的id */
        //    private Integer userid;
        //    /** 评论 */
        //    private String compnet;
        //    /** 用户名字 */
        //    private String username;
        //    /** 可以用吗 */
        //    private String isdel;
        Bookcompnet comment = new Bookcompnet();
        String username = null;
        try {
            // 解析JSON对象
            Integer bookid = Integer.parseInt(jsonMap.get("bookid"));
            String role = jsonMap.get("role");
            Integer userid = Integer.parseInt(jsonMap.get("userid"));
            String compnet = jsonMap.get("compnet");

        //不需要管id    他是自己会自增的
            username = getUserNameByRoleAndUserId(role, userid);
        comment.setBookid(bookid);
        comment.setRole(role);
        comment.setUserid(userid);
        comment.setCompnet(compnet);
        if (username != null) {
            comment.setUsername(username);
        } else {
            // 如果没有找到用户名，返回错误
            return Result.error("500","用户名获取失败");
        }
        comment.setIsdel("可用"); // 假设默认值

        // 保存评论
            bookcompnetService.add(comment);


        // 返回成功响应
        return Result.success("评论上传成功");
    } catch (Exception e) {
        // 异常处理
        return Result.error("500","评论上传失败：" + e.getMessage());
    }

    }

    @GetMapping("/getcomments")
    public Result listCommentsByBookid(@RequestParam("bookid") Integer bookid) {
        try {
            // 调用服务层方法查询评论列表
            List<Bookcompnet> comments = bookcompnetService.findByBookid(bookid);
            // 返回评论列表
            return Result.success(comments);
        } catch (Exception e) {
            // 异常处理
            return Result.error("500", "查询评论失败：" + e.getMessage());
        }
    }

    private String getUserNameByRoleAndUserId(String role, Integer userid) {
        // 根据role选择服务并获取用户信息

        if ("student".equals(role)) {

            return studentService.selectById(userid).getName();
        } else if ("teacher".equals(role)) {
            return teacherService.selectById(userid).getName();
        }
        // 可以根据需要添加更多角色的处理
        return null;
    }
}






