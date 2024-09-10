package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 课程
 */
@Data
@TableName("course")
public class Course  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 课程名 */
	@Alias("课程名")
	private String coursename;
	/** 老师 */
	@TableField(exist = false)
	private String teacher;
	/** 学期 */
	@Alias("学期")
	private String semester;
	/** 年份 */
	@Alias("年份")
	private String year;
	/** 学分 */
	@Alias("学分")
	private String credits;
	/** 课程描述 */
	@Alias("课程描述")
	private String coursedescription;
	/** 容纳学生数量 */
	@Alias("容纳学生数量")
	private String maxstudent;
	/** 现在的学生数量 */
	@Alias("现在的学生数量")
	private String nowstudentNum;
	/** 上课教室 */
	@Alias("上课教室")
	private String classroom;
	/** 时间表 */
	@Alias("时间表")
	private String schedule;
	/** 课程状态 */
	@Alias("课程状态")
	private String coursestatus;
	/** 教师Id */
	private String teacherId;
	@Alias("课程村存在")
	private String isdel;


}
