package com.example.service.impl;

import com.example.entity.MinioDocuments;
import com.example.entity.ReturnT;
import com.example.mapper.MinioDocumentsMapper;
import com.example.service.MinioDocumentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description minio_documents
 * @author lei.zhang
 * @date 2024-10-28
 */
@Service
public class MinioDocumentsServiceImpl implements MinioDocumentsService {

    @Resource
    private MinioDocumentsMapper minioDocumentsMapper;


    @Override
    public Object insert(MinioDocuments minioDocuments) {

        // valid
        if (minioDocuments == null) {
            return ReturnT.error("必要参数缺失");
        }

        minioDocumentsMapper.insert(minioDocuments);
        return ReturnT.success();
    }


    @Override
    public Object delete(int id) {
        int ret = minioDocumentsMapper.delete(id);
        return ret>0?ReturnT.success():ReturnT.error();
    }


    @Override
    public Object update(MinioDocuments minioDocuments) {
        int ret = minioDocumentsMapper.update(minioDocuments);
        return ret>0?ReturnT.success():ReturnT.error();
    }


    @Override
    public MinioDocuments load(int id) {
        return minioDocumentsMapper.load(id);
    }


    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {

        List<MinioDocuments> pageList = minioDocumentsMapper.pageList(offset, pagesize);
        int totalCount = minioDocumentsMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}