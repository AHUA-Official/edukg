package com.example.entity;

import java.util.Date;

/**
 * 消息模板表
 */
public class TblMessageTpm {
    /** ID */
    private Integer id;
    /** id\ */
    private String templateId;
    /** 模板类型 */
    private String templateType;
    /** 主题标题 */
    private String templateTopic;
    /** 正文 */
    private String content;
    /** 设定 */
    private Date createTime;
    /** 设定 */
    private String createUser;
    /** 设定 */
    private Date updateTime;
    /** 设定 */
    private String updateUser;
    /** 状态 */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateTopic() {
        return templateTopic;
    }

    public void setTemplateTopic(String templateTopic) {
        this.templateTopic = templateTopic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}