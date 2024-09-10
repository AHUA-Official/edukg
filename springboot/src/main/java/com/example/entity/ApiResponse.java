package com.example.entity;
public class ApiResponse<T> {
    private int status;
    private String reason;
    private T data;

    public ApiResponse(int status, String reason, T data) {
        this.status = status;
        this.reason = reason;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}