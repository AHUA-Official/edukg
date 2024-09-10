package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/edukgcourse")
public class edukgCourseController {

    @Resource
    CourseService courseService;

    @GetMapping("/select")
    public Result selectAll(Course course) {
        List<Course> list = courseService.selectAll();
        return Result.success(list);
    }

    @GetMapping("/selectbyteacherid")
    public Result selectbyteacherid(@RequestParam(value = "teacherId", required = false, defaultValue = "8") Integer teacherId) {
        System.out.println(teacherId);
        List<Course> allCourses = courseService.selectAll(); // 假设这个方法返回所有课程
        System.out.println(allCourses.toString());
        System.out.println(teacherId);

        String teacherIdStr = String.valueOf(teacherId); // 将 teacherId 转换成字符串

        System.out.println(allCourses.toString());
        System.out.println(teacherIdStr);

        // 使用 Stream API 过滤掉特定 teacher_id 的课程，使用字符串比较
        List<Course> filteredCourses = allCourses.stream()
                .filter(course -> String.valueOf(course.getTeacherId()).equals(teacherIdStr))
                .collect(Collectors.toList());
        // 使用 Stream API 过滤掉特定 teacher_id 的课程
        //再从这个里面把
        List<String> courseNames = filteredCourses.stream()
                .map(Course::getCoursename)
                .collect(Collectors.toList());
        System.out.println(filteredCourses.toString());

        return Result.success(courseNames);
    }


}