package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 知识图谱关系类型表
 */
@Data
@TableName("graphrelationtype")
public class Graphrelationtype  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 关系类型id */
	@Alias("关系类型id")
	private String relationtypeid;
	/** 关系类型的名字 */
	@Alias("关系类型的名字")
	private String relationtypename;
	/** 关系方向性 */
	@Alias("关系方向性")
	private String bidirectional;
	/** 权重的属性 */
	@Alias("权重的属性")
	private String weight;



}
