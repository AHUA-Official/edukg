package com.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    private String role;
    private String user_jub_num;
    private String login_type;
    private String password;
    @JsonProperty("userinfo") // 映射JSON字段名
    private UserInfo userInfo;
    private String  verification_code;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    private String  user_name;
    private String user_email;
    private String  user_phone;


    @Override
    public String toString() {
        return "RegisterRequest{" +
                "role='" + role + '\'' +
                ", user_jub_num='" + user_jub_num + '\'' +
                ", login_type='" + login_type + '\'' +
                ", password='" + password + '\'' +
                ", userInfo=" + userInfo +
                ", verification_code='" + verification_code + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_phone='" + user_phone + '\'' +
                '}';
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }
// 构造函数、Getter和Setter省略，可以使用Lombok注解自动生成
    // 或者手动编写

    public RegisterRequest() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_jub_num() {
        return user_jub_num;
    }

    public void setUser_jub_num(String user_jub_num) {
        this.user_jub_num = user_jub_num;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}