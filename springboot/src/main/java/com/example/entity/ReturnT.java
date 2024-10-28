package com.example.entity;

public class ReturnT<T> {
    private int code;
    private String message;
    private T data;

    // 省略构造器、getter和setter

    public static <T> ReturnT<T> success() {
        ReturnT<T> result = new ReturnT<>();
        result.setCode(200);
        result.setMessage("Success");
        return result;
    }

    private void setCode(int i) {
    }
    // Getter和Setter方法
    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    // 成功的静态方法，允许携带数据
    public static <T> ReturnT<T> success(T data) {
        return success(200, "Success", data);
    }

    // 成功的静态方法，允许自定义消息
    public static <T> ReturnT<T> success(String message) {
        return success(200, message, null);
    }

    // 成功的静态方法，允许自定义消息和数据
    public static <T> ReturnT<T> success(String message, T data) {
        return success(200, message, data);
    }

    // 错误的静态方法
    public static <T> ReturnT<T> error() {
        return error(null);
    }

    // 错误的静态方法，允许携带数据
    public static <T> ReturnT<T> error(T data) {
        return error(500, "Error", data);
    }

    // 错误的静态方法，允许自定义消息
    public static <T> ReturnT<T> error(String message) {
        return error(500, message, null);
    }

    // 错误的静态方法，允许自定义消息和数据
    public static <T> ReturnT<T> error(String message, T data) {
        return error(500, message, data);
    }

    // 成功和错误的静态方法，允许自定义代码、消息和数据
    public static <T> ReturnT<T> success(int code, String message, T data) {
        ReturnT<T> result = new ReturnT<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> ReturnT<T> error(int code, String message, T data) {
        ReturnT<T> result = new ReturnT<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}