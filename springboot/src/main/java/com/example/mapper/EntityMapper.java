package com.example.mapper;

import com.example.entity.Entity;
import java.util.List;

/**
 * 操作entity相关数据接口
 */
public interface EntityMapper {

    /**
     * 新增
     */
    int insert(Entity entity);
    int insertwithname(Entity entity);
    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Entity entity);

    /**
     * 根据ID查询
     */
    Entity selectById(Integer id);

    Entity selectByEntity(String entity);

    /**
     * 查询所有
     */
    List<Entity> selectAll(Entity entity);

}