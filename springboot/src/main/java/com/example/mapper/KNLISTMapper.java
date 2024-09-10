package com.example.mapper;

import com.example.entity.KNLIST;
import java.util.List;

/**
 * 操作KNLIST相关数据接口
 */
public interface KNLISTMapper {

    /**
     * 新增
     */
    int insert(KNLIST KNLIST);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(KNLIST KNLIST);

    /**
     * 根据ID查询
     */
    KNLIST selectById(Integer id);

    /**
     * 查询所有
     */
    List<KNLIST> selectAll(KNLIST KNLIST);

}