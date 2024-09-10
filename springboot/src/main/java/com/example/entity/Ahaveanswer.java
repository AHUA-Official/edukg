package com.example.entity;

import java.util.Date;

/**
 * 已经有了答案的表
 */
public class Ahaveanswer {
    /** ID */
    private Integer id;
    /** 问题的文本信息 */
    private String questiontext;
    /** 提升 */
    private String prompt;
    /** agent */
    private String agent;
    /** 问问题的时间 */
    private Date asktime;
    /** 当前的轮状状态 */
    private Integer genestatus;
    /** 问题被回答的时间 */
    private Date answertime;
    /** 问题的真是回答 */
    private String answer;
    /** 实体表里面匹配到的东西，当前需求不高 */
    private String tag;
    /** 父问题ID */
    private Integer parentid;
    /** 上下文相关，其实目前这个也是摆设 */
    private String context;
    /** 用户满意度 */
    private Integer satisfaction;
    /** 附件，目前没有用 */
    private String attachment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getAsktime() {
        return asktime;
    }

    public void setAsktime(Date asktime) {
        this.asktime = asktime;
    }

    public Integer getGenestatus() {
        return genestatus;
    }

    public void setGenestatus(Integer genestatus) {
        this.genestatus = genestatus;
    }

    public Date getAnswertime() {
        return answertime;
    }

    public void setAnswertime(Date answertime) {
        this.answertime = answertime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}