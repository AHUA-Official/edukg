package com.example.entity;
//  定义聊天的POJO
//  private String userId;
//    private Boolean wsCloseFlag;
//
//还有   question []
//还有answer  []


import org.springframework.context.annotation.Bean;

import java.util.List;

public class Chat {
    // 用户ID
    private String userId;

    // WebSocket关闭标志
    private Boolean wsCloseFlag;

    // 问题列表
    private List<String> questions;

    // 答案列表
    private List<String> answers;

    // 默认构造函数
    public Chat() {
    }

    // 带参数的构造函数
    public Chat(String userId, Boolean wsCloseFlag, List<String> questions, List<String> answers) {
        this.userId = userId;
        this.wsCloseFlag = wsCloseFlag;
        this.questions = questions;
        this.answers = answers;
    }

    // Getter和Setter方法
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getWsCloseFlag() {
        return wsCloseFlag;
    }

    public void setWsCloseFlag(Boolean wsCloseFlag) {
        this.wsCloseFlag = wsCloseFlag;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    // toString方法，便于打印和查看对象内容
    @Override
    public String toString() {
        return "Chat{" +
                "userId='" + userId + '\'' +
                ", wsCloseFlag=" + wsCloseFlag +
                ", questions=" + questions +
                ", answers=" + answers +
                '}';
    }
}