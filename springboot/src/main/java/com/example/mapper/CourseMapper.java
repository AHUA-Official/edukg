package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /**
      * 查询所有
    */
    List<Course> selectAll();

    /**
      * 根据ID查询
    */
    Course selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);
    /**
     * 根据教师ID查询课程列表
     */
    List<Course> selectByTeacherId(String teacherId);
/*
根据coursename;和taecheradd来删除softDeleteByCourseNameAndTeacher

 */
int softDeleteByCourseNameAndTeacher(String coursename, String teacherID);
}