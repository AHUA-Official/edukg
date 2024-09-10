package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
      * 查询所有
    */
    List<Teacher> selectAll(Teacher teacher);

    /**
      * 根据ID查询
    */
    Teacher selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);

	@Select("select * from teacher where `username` = #{name}")
	Teacher selectByUsername(@Param("name") String userName);



}