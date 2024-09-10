package com.example.entity;


import javax.naming.Name;

/**
 *
 */
public class UserInfo {
    private String user_name;
    private String user_email;
    private String user_phone;

    // 构造函数、Getter和Setter省略，可以使用Lombok注解自动生成
    // 或者手动编写

    public UserInfo() {}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    public String toString() {
        return "UserInfo{" +
                "name='" + getUser_name() + '\'' +
                ", email='" + getUser_email() + '\'' +
                ", phone='" +getUser_phone() + '\'' +
                '}';
    }



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
}
