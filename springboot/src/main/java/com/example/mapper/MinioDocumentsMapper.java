package com.example.mapper;

import com.example.entity.MinioDocuments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MinioDocumentsMapper {

    /**
     * 新增
     * @author lei.zhang
     * @date 2024/10/28
     **/
    int insert(MinioDocuments minioDocuments);

    /**
     * 刪除
     * @author lei.zhang
     * @date 2024/10/28
     **/
    int delete(int id);

    /**
     * 更新
     * @author lei.zhang
     * @date 2024/10/28
     **/
    int update(MinioDocuments minioDocuments);

    /**
     * 查询 根据主键 id 查询
     * @author lei.zhang
     * @date 2024/10/28
     **/
    MinioDocuments load(int id);

    /**
     * 查询 分页查询
     * @author lei.zhang
     * @date 2024/10/28
     **/
    List<MinioDocuments> pageList(int offset, int pagesize);

    /**
     * 查询 分页查询 count
     * @author lei.zhang
     * @date 2024/10/28
     **/
    int pageListCount(int offset,int pagesize);

}