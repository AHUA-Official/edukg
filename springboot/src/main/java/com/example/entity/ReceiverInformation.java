
package com.example.entity;

import java.util.Objects;

// ReceiverInformation 类
public class ReceiverInformation {
    private String needQuery;
    private String phone;
    private String email;

    // 为Builder类提供一个构造方法
    public ReceiverInformation(String needQuery, String phone, String email) {
        this.needQuery = needQuery;
        this.phone = phone;
        this.email = email;
    }

    // Getter 和 Setter 省略...
    public String getNeedQuery() {
        return needQuery;
    }

    public void setNeedQuery(String needQuery) {
        this.needQuery = needQuery;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiverInformation that = (ReceiverInformation) o;
        return Objects.equals(needQuery, that.needQuery) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(needQuery, phone, email);
    }
    // equals 和 hashCode 方法省略...
}