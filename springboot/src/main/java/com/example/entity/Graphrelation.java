package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 知识图谱关系表
 */
@Data
@TableName("graphrelation")
public class Graphrelation  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 关系id */
	@Alias("关系id")
	private String relationid;
	/** 头id */
	@TableField(exist = false)
	private String headid;
	/** 尾巴id */
	@Alias("尾巴id")
	private String tailid;
	/** 关系类型 */
	@TableField(exist = false)
	private String typeid;
	/** Neo4关系id */
	@Alias("Neo4关系id")
	private String neo4jid;
	/** 关系的属性json */
	private String relationjson;
	/** 关系权重 */
	@Alias("关系权重")
	private String weight;
	/** 创建时间 */
	@Alias("创建时间")
	private String createat;
	/** 更新时间 */
	@Alias("更新时间")
	private String updateat;
	/** 被删除 */
	@Alias("被删除")
	private String isdel;
	/** 知识图谱关系类型表Id */
	private Integer graphrelationtypeId;
	/** 知识图谱节点表Id */
	private Integer graphnodeId;



}
