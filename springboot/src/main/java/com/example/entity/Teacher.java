package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 教师
 */
@Data
@TableName("teacher")
public class Teacher extends Account {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer   id;
	/** 账号 */
	@Alias("账号")
	private String username;
	/** 教师工号 */
	@Alias("教师工号")
	private String userJubNum;
	/** 名称 */
	@Alias("名称")
	private String name;
	/** 密码 */
	private String password;
	/** 邮箱 */
	@Alias("邮箱")
	private String email;
	/** 手机 */
	@Alias("手机")
	private String phone;
	/** 头像 */
	private String avatar;
	/**  可用的登录方式  */
	@Alias(" 可用的登录方式 ")
	private Integer loginType;
	/**  是否删除 */
	@Alias(" 是否删除")
	private Integer deleted;
	/** 创建时间 */
	@Alias("创建时间")
	private String createdAt;
	/** 更新时间 */
	@Alias("更新时间")
	private String updatedAt;



}
