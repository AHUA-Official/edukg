package com.example.entity;

import java.util.Date;

/**
 * 文件注册记录表
 */
public class PDFUploadRecord {
    /** ID */
    private Integer id;
    /** 存名 */
    private String storedName;
    /** 原名 */
    private String originalName;
    /** 上传时间 */
    private Date uploadTime;
    /** 上传用户 */
    private String uploadUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoredName() {
        return storedName;
    }

    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

}