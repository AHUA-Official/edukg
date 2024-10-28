package com.example.entity;

import java.io.Serializable;

public class MinioDocuments implements Serializable {

    private static final long serialVersionUID = 1L;


    private String document_name;


    private String minio_url;


    private String symbol;


    private String preview;


    private boolean need_parse;


    public MinioDocuments() {
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getMinio_url() {
        return minio_url;
    }

    public void setMinio_url(String minio_url) {
        this.minio_url = minio_url;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public boolean getNeed_parse() {
        return need_parse;
    }

    public void setNeed_parse(boolean need_parse) {
        this.need_parse = need_parse;
    }

}