package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 知识图谱类型表
 */
@Data
@TableName("knowledgehraphtype")
public class Knowledgehraphtype  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 图谱类型的唯一标识 */
	@Alias("图谱类型的唯一标识")
	private String graphtypeid;
	/** 类型名称 */
	@Alias("类型名称")
	private String typename;
	/** 简短描述 */
	@Alias("简短描述")
	private String description;
	/** 知识图谱来源 */
	@Alias("知识图谱来源")
	private String sourceurl;
	/** 关联资料 */
	@TableField(exist = false)
	private String linkedmaterial;
	/** 图谱类型的图标路径 */
	@Alias("图谱类型的图标路径")
	private String iconpath;
	/** 创建时间 */
	@Alias("创建时间")
	private String createtime;
	/** 更新时间 */
	@Alias("更新时间")
	private String updateat;
	/** 是否删除 */
	@Alias("是否删除")
	private String isdeleted;
	/** 课程资料Id */
	private Integer materialId;



}
