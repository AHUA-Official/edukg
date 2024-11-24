package com.example.service;

import com.example.common.config.TokenUtils;
import com.example.entity.Account;
import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * 新增
     */
    @Transactional
    public void add(Course course) {
        courseMapper.insert(course);
    }

    /**
     * 删除
     */
    @Transactional
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }
    @Transactional
    public boolean deleteBynameandtaecher(String   name,String teacherID) {
        int rowsAffected = courseMapper.softDeleteByCourseNameAndTeacher(name, teacherID);
        return rowsAffected > 0;
    }

    //课程检查喵
    public boolean checkCourse(Course course) {
        if (course == null) {
            return false;
        }

        if (course.getCoursename() == null || course.getCoursename().trim().isEmpty()) {
            return false;
        }

        if (course.getTeacher() == null || course.getTeacher().trim().isEmpty()) {
            return false;
        }

        if (course.getSemester() == null || course.getSemester().trim().isEmpty()) {
            return false;
        }

        if (course.getYear() == null || course.getYear().trim().isEmpty()) {
            return false;
        }

        if (course.getCredits() == null || course.getCredits().trim().isEmpty()) {
            return false;
        }

        if (course.getCoursedescription() == null || course.getCoursedescription().trim().isEmpty()) {
            return false;
        }

        if (course.getMaxstudent() == null || course.getMaxstudent().trim().isEmpty()) {
            return false;
        }

        if (course.getNowstudentNum() == null || course.getNowstudentNum().trim().isEmpty()) {
            return false;
        }

        if (course.getClassroom() == null || course.getClassroom().trim().isEmpty()) {
            return false;
        }

        if (course.getSchedule() == null || course.getSchedule().trim().isEmpty()) {
            return false;
        }

        if (course.getCoursestatus() == null || course.getCoursestatus().trim().isEmpty()) {
            return false;
        }

        if (course.getTeacherId() == null || course.getTeacherId().trim().isEmpty()) {
            return false;
        }

        if (course.getIsdel() == null || course.getIsdel().trim().isEmpty()) {
            return false;
        }

        return true;
    }
    /**
     * 批量删除
     */
    @Transactional
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            courseMapper.deleteById(id);
        }
    }
    @Transactional
    public List<Course> selectByTeacherId(String teacherId) {
        return courseMapper.selectByTeacherId(teacherId);
    }
    /**
     * 修改
     */
    @Transactional
    public void updateById(Course course) {
        courseMapper.updateById(course);
    }

    /**
     * 根据ID查询
     */
    @Transactional
    public Course selectById(Integer id) {
        return courseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Transactional
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    /**
     * 分页查询
     */
    @Transactional
    public PageInfo<Course> selectPage(Course course, Integer pageNum, Integer pageSize) {
		Account currentUser = TokenUtils.getCurrentUser();
		if ("teacher".equals(currentUser.getRole())) {
			course.setTeacherId(String.valueOf(currentUser.getId()));
		}

        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = this.selectAll();

        return PageInfo.of(list);
    }

}