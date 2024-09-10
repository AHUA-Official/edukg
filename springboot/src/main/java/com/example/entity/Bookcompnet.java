package com.example.entity;


/**
 * 阅读评论表
 */
public class Bookcompnet {
    /** ID */
    private Integer id;
    /** 书本id */
    private Integer bookid;
    /** 角色 是student就去student */
    private String role;
    /** 角色在各自表里面的id */
    private Integer userid;
    /** 评论 */
    private String compnet;
    /** 用户名字 */
    private String username;
    /** 可以用吗 */
    private String isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCompnet() {
        return compnet;
    }

    public void setCompnet(String compnet) {
        this.compnet = compnet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

}