package com.example.entity;

// ReceiverInformationBuilder 类
public class ReceiverInformationBuilder {
    private String needQuery = "false";
    private String phone = "15282149533";
    private String email = "3309719563@qq.com";

    public ReceiverInformationBuilder withNeedQuery(String needQuery) {
        this.needQuery = needQuery;
        return this;
    }

    public ReceiverInformationBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ReceiverInformationBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    // 确保ReceiverInformation的构造器是可访问的，这里假设是包访问权限，如果是不同包需要调整访问权限
    public ReceiverInformation build() {
        return new ReceiverInformation(needQuery, phone, email);
    }
}
