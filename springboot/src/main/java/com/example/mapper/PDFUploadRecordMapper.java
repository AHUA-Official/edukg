package com.example.mapper;

import com.example.entity.PDFUploadRecord;
import java.util.List;

/**
 * 操作PDFUploadRecord相关数据接口
 */
public interface PDFUploadRecordMapper {

    /**
     * 新增
     */
    int insert(PDFUploadRecord PDFUploadRecord);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(PDFUploadRecord PDFUploadRecord);

    /**
     * 根据ID查询
     */
    PDFUploadRecord selectById(Integer id);

    /**
     * 查询所有
     */
    List<PDFUploadRecord> selectAll(PDFUploadRecord PDFUploadRecord);

}