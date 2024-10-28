package com.example.controller;

import com.example.entity.MinioDocuments;
import com.example.service.MinioDocumentsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.Map;

/**
 * @description minio_documents
 * @author lei.zhang
 * @date 2024-10-28
 */
@RestController
@RequestMapping(value = "/minioDocuments")
public class testminioducumentsController {

    @Resource
    private MinioDocumentsService minioDocumentsService;

    /**
     * 新增
     * @author lei.zhang
     * @date 2024/10/28
     **/
    @RequestMapping("/insert")
    public Object insert(MinioDocuments minioDocuments){
        return minioDocumentsService.insert(minioDocuments);
    }

    /**
     * 刪除
     * @author lei.zhang
     * @date 2024/10/28
     **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return minioDocumentsService.delete(id);
    }

    /**
     * 更新
     * @author lei.zhang
     * @date 2024/10/28
     **/
    @RequestMapping("/update")
    public Object update(MinioDocuments minioDocuments){
        return minioDocumentsService.update(minioDocuments);
    }

    /**
     * 查询 根据主键 id 查询
     * @author lei.zhang
     * @date 2024/10/28
     **/
    @RequestMapping("/load")
    public Object load(int id){
        return minioDocumentsService.load(id);
    }

    /**
     * 查询 分页查询
     * @author lei.zhang
     * @date 2024/10/28
     **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return minioDocumentsService.pageList(offset, pagesize);
    }

}