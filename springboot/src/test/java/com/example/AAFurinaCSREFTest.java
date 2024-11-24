package com.example;


import com.example.entity.AAFurinaCSREF;
import com.example.entity.AAFurinaminiodocuments;
import com.example.entity.Course;
import com.example.mapper.AAFurinaminioMapper;
import com.example.service.AAFurinaCSREFService;
import com.example.service.AAFurinaminioService;
import com.example.service.CourseService;
import io.minio.*;

import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AAFurinaCSREFTest {

    @Resource
    private AAFurinaCSREFService courseStudentrefService;



    @Autowired
    private CourseService courseService;


    @Test
    public void testAddCourseStudentref() {


        // 创建课程
        Course course = new Course();
        course.setCoursename("Mathematics");
        course.setTeacher("Dr. Smith");
        course.setSemester("Fall");
        course.setYear("2023");
        course.setCredits("4");
        course.setCoursedescription("Introduction to Mathematics");
        course.setMaxstudent("30");
        course.setNowstudentNum("0");
        course.setClassroom("Room 101");
        course.setSchedule("MWF 9:00 AM - 10:15 AM");
        course.setCoursestatus("Active");
        course.setTeacherId("1");
        courseService.add(course);

        // 创建课程学生关联
        AAFurinaCSREF courseStudentref = new AAFurinaCSREF();
        courseStudentref.setUsername("2022090917008");
        courseStudentref.setName("TestUser");
        courseStudentref.setPhone("1234567890");
        courseStudentref.setCourseId(course.getId());
        courseStudentref.setCoursename("Mathematics");

        // 添加课程学生关联
        courseStudentrefService.add(courseStudentref);

        // 验证是否成功添加
        List<AAFurinaCSREF> AAFurinaCSREF  = courseStudentrefService.selectAll(new AAFurinaCSREF());
        System.out.println(AAFurinaCSREF);

    }

}
