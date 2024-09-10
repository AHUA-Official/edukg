package com.example.mapper;

import com.example.entity.Triple;
import java.util.List;

/**
 * 操作Triple相关数据接口
 */
public interface TripleMapper {

    /**
     * 新增
     */
    int insert(Triple Triple);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Triple Triple);

    /**
     * 根据ID查询
     */
    Triple selectById(Integer id);

    /**
     * 查询所有
     */
    List<Triple> selectAll(Triple Triple);

}