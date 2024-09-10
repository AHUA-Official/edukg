package com.example.entity;


/**
 * 课程资料
 */
public class Booknext {
    /** ID */
    private Integer id;
    /** 文档名字 */
    private String docname;
    /** 文档简介 */
    private String docmary;
    /** 文档路径 */
    private String docpath;
    /** 上传的人 */
    private String uploadone;
    /** 课程id */
    private Integer courseid;
    /** 课程名字 */
    private String coursename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocmary() {
        return docmary;
    }

    public void setDocmary(String docmary) {
        this.docmary = docmary;
    }

    public String getDocpath() {
        return docpath;
    }

    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }

    public String getUploadone() {
        return uploadone;
    }

    public void setUploadone(String uploadone) {
        this.uploadone = uploadone;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = Integer.parseInt(courseid);
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

}