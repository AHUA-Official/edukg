package com.example.entity;

import java.util.Objects;

 public  class SenderRecipient {
    private String phone;
    private String email;
    private String siteId;

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

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SenderRecipient that = (SenderRecipient) o;
        return Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(siteId, that.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email, siteId);
    }
}
