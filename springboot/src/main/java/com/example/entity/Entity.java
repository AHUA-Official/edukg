package com.example.entity;


/**
 * 知识实体表
 */
public class Entity {
    /** ID */
    private Integer id;
    /** 实体名称 */
    private String entity;
    /** 实体类型 */
    private String type;
    /** ll */
    private String tag;
    /** 摘要 */
    private String description;
    /** 来源 */
    private String source;
    /** 分数 */
    private Integer score;
    /** 状态 */
    private String status;
    /** 层级关系 */
    private Integer parentId;
    /** 外号 */
    private String alias;
    /** 出现次数 */
    private Integer apeartime;
    /** 有没有被确认验证过 */
    private String verification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getApeartime() {
        return apeartime;
    }

    public void setApeartime(Integer apeartime) {
        this.apeartime = apeartime;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
    public void initializeDefaultValues() {
        if (this.type == null) {
            this.type = "DEFAULT_TYPE"; // 假设的默认类型
        }
        if (this.tag == null) {
            this.tag = "DEFAULT_TAG"; // 假设的默认标签
        }
        if (this.description == null) {
            this.description = "DEFAULT_DESCRIPTION"; // 假设的默认描述
        }
        if (this.source == null) {
            this.source = "DEFAULT_SOURCE"; // 假设的默认来源
        }
        if (this.score == null) {
            this.score = 0; // 默认分数
        }
        if (this.status == null) {
            this.status = "DEFAULT_STATUS"; // 假设的默认状态
        }
        if (this.parentId == null) {
            this.parentId = 0; // 默认父ID
        }
        if (this.alias == null) {
            this.alias = "DEFAULT_ALIAS"; // 假设的默认外号
        }
        if (this.apeartime == null) {
            this.apeartime = 0; // 默认出现次数
        }
        if (this.verification == null) {
            this.verification = "UNVERIFIED"; // 默认未验证状态
        }
        // id 字段通常由数据库自动生成，所以不需要在这里设置
    }
}