package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 知识图谱节点表
 */
@Data
@TableName("graphnode")
public class Graphnode  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 节点id */
	@Alias("节点id")
	private String nodeid;
	/** 节点类型 */
	@TableField(exist = false)
	private String nodetype;
	/** neo4j标识 */
	@Alias("neo4j标识")
	private String graphnodeid;
	/** 节点属性 */
	@Alias("节点属性")
	private String propertiesjson;
	/** 文档知识支撑 */
	@Alias("文档知识支撑")
	private String docmennt;
	/** 创建时间 */
	@Alias("创建时间")
	private String createtime;
	/** 更新时间 */
	@Alias("更新时间")
	private String updateat;
	/** 被删除了吗 */
	@Alias("被删除了吗")
	private String isdel;
	/** 知识图谱节点类型表Id */
	private Integer graphnodetypeId;



}
