package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 学生
 */
@Data
@TableName("student")
public class Student extends Account {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 账号 */
    @Alias("账号")
    private String username;
    /** 密码 */
    private String password;
    /** 名称 */
    @Alias("名称")
    private String name;
    /** 头像 */
    private String avatar;
    /** 手机 */
    @Alias("手机")
    private String phone;
    /** 邮箱 */
    @Alias("邮箱")
    private String email;
    /** 更新时间 */
    @Alias("更新时间")
    private String updatedAt;
    /** 创建时间 */
    @Alias("创建时间")
    private String createAt;
    /** 逻辑删除 */
    @Alias("逻辑删除")
    private String deleted;
    /** 可用登录方式 */
    @Alias("可用登录方式")
    private String logintype;
    /** 专业 */
    @Alias("专业")
    private String major;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
