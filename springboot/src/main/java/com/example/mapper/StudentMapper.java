package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.example.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    /**
      * 查询所有
    */
    List<Student> selectAll(Student student);

    /**
      * 根据ID查询
    */
    Student selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);


	Student selectByUsername(@Param("name") String userName);



}