package com.example.service;

import com.example.entity.MinioDocuments;

import java.util.Map;

/**
 * @description minio_documents
 * @author lei.zhang
 * @date 2024-10-28
 */
public interface MinioDocumentsService {

    /**
     * 新增
     */
    public Object insert(MinioDocuments minioDocuments);



    /**
     * 删除
     */
    public Object delete(int id);

    /**
     * 更新
     */
    public Object update(MinioDocuments minioDocuments);

    /**
     * 根据主键 id 查询
     */
    public MinioDocuments load(int id);

    /**
     * 分页查询
     * @return
     */
    public Map<String, Object> pageList(int offset, int pagesize);

}