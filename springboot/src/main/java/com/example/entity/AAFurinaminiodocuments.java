package com.example.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AAFurinaminiodocuments {

    private String document_id;
    /** 文档名 */
    private String documentName;
    /** 文档地址 */
    private String minioUrl;
    /** 标识 */
    private String symbol;
    /** 待解析状态 */
    private String needParse;
    /** 预览 */
    private String preview;

    public String getId() {
        return document_id;
    }

    public void setId(String id) {
        this.document_id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getMinioUrl() {
        return minioUrl;
    }

    public void setMinioUrl(String minioUrl) {
        this.minioUrl = minioUrl;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNeedParse() {
        return needParse;
    }

    public void setNeedParse(String needParse) {
        this.needParse = needParse;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    private static final Set<String> generatedUUIDs = Collections.synchronizedSet(new HashSet<>());

    public static String generateUniqueUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (generatedUUIDs.contains(uuid));

        generatedUUIDs.add(uuid);
        return uuid;
    }
    public AAFurinaminiodocuments(String documentName, String minioUrl, String symbol, String needParse, String preview) {
        this.document_id = generateUniqueUUID();
        this.documentName = documentName;
        this.minioUrl = minioUrl;
        this.symbol = symbol;
        this.needParse = needParse;
        this.preview = preview;
    }

}
