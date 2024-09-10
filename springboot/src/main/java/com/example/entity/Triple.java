package com.example.entity;


/**
 * 三元组表
 */
public class Triple {
    /** ID */
    private Integer id;
    /** 主语 */
    private String Subject;
    /** 关系 */
    private String edge;
    /** 宾语 */
    private String target;
    /** 主语id */
    private Integer Subjectid;
    /** 宾语id */
    private Integer targetid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getSubjectid() {
        return Subjectid;
    }

    public void setSubjectid(Integer Subjectid) {
        this.Subjectid = Subjectid;
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
    }

}