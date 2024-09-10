package com.example.mapper;

import com.example.entity.Booknext;
import java.util.List;

/**
 * 操作booknext相关数据接口
 */
public interface BooknextMapper {

    /**
     * 新增
     */
    int insert(Booknext booknext);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Booknext booknext);

    /**
     * 根据ID查询
     */
    Booknext selectById(Integer id);

    /**
     * 查询所有
     */
    List<Booknext> selectAll(Booknext booknext);

    List<Booknext> selectbycourseid(Integer id);

}