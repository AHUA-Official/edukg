package com.example.entity;

import java.util.Date;

/**
 * 知识清单表
 */
public class KNLIST {
    /** ID */
    private Integer id;
    /** 书本id */
    private String bookid;
    /** 用户id */
    private String usrid;
    /** 课程id */
    private String courseid;
    /** 标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 存储路径 */
    private String loadpath;
    /** 创建时间 */
    private Date createat;
    /** 更新时间 */
    private Date updateat;
    /** 是否删除 */
    private String isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLoadpath() {
        return loadpath;
    }

    public void setLoadpath(String loadpath) {
        this.loadpath = loadpath;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

}