package com.example.service;

import com.example.common.enums.ResultCodeEnum;
import com.example.entity.AAFurinaCSREF;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.AAFurinaCSREFMapper;
import com.example.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生课程表业务处理
 **/
@Service
public class AAFurinaCSREFService {

    @Resource
    private AAFurinaCSREFMapper courseStudentrefMapper;
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    public List<AAFurinaCSREF> selectAll(AAFurinaCSREF CSREF) {
        return courseStudentrefMapper.selectAll(CSREF);
    }




    @Transactional
    public  Map<String, Object> add(AAFurinaCSREF courseStudentref) {
        Map<String, Object> result = new HashMap<>();
        List<AAFurinaCSREF> failList = new ArrayList<>();
        List<String> genestory = new ArrayList<>();

        try {
            // 检查学生是否存在
            Student student = studentService.selectByUsername(courseStudentref.getUsername());
            if (student == null) {
                genestory.add("学生不存在");
                // 创建新学生
                student = new Student();
                student.setUsername(courseStudentref.getUsername());
                student.setName(courseStudentref.getName());
                student.setPhone(courseStudentref.getPhone());
                studentService.add(student); // 调用 StudentService 的 add 方法
                courseStudentref.setStudentId(student.getId());
            } else {
                courseStudentref.setStudentId(student.getId());
                genestory.add("学生存在");
            }

            // 检查课程是否存在
            Course course = courseService.selectById(courseStudentref.getCourseId());
            if (course == null) {
                genestory.add("课程不存在");
                failList.add(courseStudentref);
                result.put("failList", failList);
                result.put("genestory", genestory);
                return result;
            }

            // 插入关联记录
            courseStudentrefMapper.insert(courseStudentref);
        } catch (Exception e) {
            genestory.add("操作失败: " + "已经存在该记录  不可以进行重复添加"+e.getMessage());
            failList.add(courseStudentref);
        }

        // 如果成功，可以选择是否返回failList和genestory，或者返回空列表表示没有错误
        result.put("failList", failList);
        result.put("genestory", genestory);
        System.out.println(result);
        return result;
    }




    @Transactional
    public Map<String, Object> batchAdd(List<AAFurinaCSREF> courseStudentrefs) {
        Map<String, Object> result = new HashMap<>();
        List<AAFurinaCSREF> failList = new ArrayList<>();
        List<String> genestory = new ArrayList<>();

        for (AAFurinaCSREF courseStudentref : courseStudentrefs) {
            try {
                // 检查学生是否存在
                Student student = studentService.selectByUsername(courseStudentref.getUsername());
                if (student == null) {
                    genestory.add("学生不存在"+courseStudentref.getUsername());
                    // 创建新学生
                    student = new Student();
                    student.setUsername(courseStudentref.getUsername());
                    student.setName(courseStudentref.getName());
                    student.setPhone(courseStudentref.getPhone());
                    studentService.add(student); // 调用 StudentService 的 add 方法
                    courseStudentref.setStudentId(student.getId());
                } else {
                    courseStudentref.setStudentId(student.getId());
                    genestory.add("学生存在"+courseStudentref.getUsername());
                }

                // 检查课程是否存在
                Course course = courseService.selectById(courseStudentref.getCourseId());
                if (course == null) {
                    genestory.add("课程不存在");
                    failList.add(courseStudentref);
                    continue;
                }

                // 插入关联记录
                courseStudentrefMapper.insert(courseStudentref);
            }  catch (DuplicateKeyException e) {
            genestory.add("记录已存在");
            // 不将记录已存在的对象加入 failList
        } catch (Exception e) {
            genestory.add("操作失败: " + e.getMessage());
            failList.add(courseStudentref);
        }
        }

        // 返回结果
        result.put("failList", failList);
        result.put("genestory", genestory);
        System.out.println(result);
        return result;
    }

    public List<AAFurinaCSREF> selectbycourseid(Integer courseId) {
        return courseStudentrefMapper.selectbycourseid(courseId);
    }
}

