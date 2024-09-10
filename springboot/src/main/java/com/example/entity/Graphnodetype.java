package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 知识图谱节点类型表
 */
@Data
@TableName("graphnodetype")
public class Graphnodetype  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 节点类型id */
	@Alias("节点类型id")
	private String nodetypeid;
	/** 节点类型名称 */
	@Alias("节点类型名称")
	private String typename;
	/** 否具有层级结构 */
	@Alias("否具有层级结构")
	private String ishierarchical;
	/** 图标路径 */
	@Alias("图标路径")
	private String iconpath;
	/** 创建时间 */
	@Alias("创建时间")
	private String createat;
	/** 最后更新时间 */
	@Alias("最后更新时间")
	private String updatetime;
	/** 是否被删除 */
	@Alias("是否被删除")
	private String isdel;



}
