package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 课程资料
 */
@Data
@TableName("material")
public class Material  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 课程 */
	@TableField(exist = false)
	private String course;
	/** 资料名 */
	@Alias("资料名")
	private String docname;
	/** 资料简介 */
	@Alias("资料简介")
	private String docsmary;
	/** 资料路径 */
	@Alias("资料路径")
	private String docpath;
	/** 上传人 */
	@Alias("上传人")
	private String uplodeedby;
	/** 上传时间 */
	@Alias("上传时间")
	private String uploadedtime;
	/** 资料类型 */
	@Alias("资料类型")
	private String contentype;
	/** 资料标签 */
	@Alias("资料标签")
	private String doctag;
	/** 资料版本 */
	@Alias("资料版本")
	private String docversion;
	/** 资料用途 */
	@Alias("资料用途")
	private String docpurpose;
	/** 课程Id */
	private Integer courseId;



}
