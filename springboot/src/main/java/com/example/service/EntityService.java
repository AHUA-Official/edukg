package com.example.service;

import com.example.entity.Entity;
import com.example.mapper.EntityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 知识实体表业务处理
 **/
@Service
public class EntityService {

    @Resource
    private EntityMapper entityMapper;

    /**
     * 新增
     */
    public void add(Entity entity) {
        entityMapper.insert(entity);
    }


    /**
     * 删除
     */
    public void deleteById(Integer id) {
        entityMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            entityMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Entity entity) {
        entityMapper.updateById(entity);
    }

    /**
     * 根据ID查询
     */
    public Entity selectById(Integer id) {
        return entityMapper.selectById(id);
    }

    public Entity selectByEntity(String  entity) {
        return entityMapper.selectByEntity(entity);
    }

    /**
     * 查询所有
     */
    public List<Entity> selectAll(Entity entity) {
        return entityMapper.selectAll(entity);
    }

    /**
     * 分页查询
     */
    public PageInfo<Entity> selectPage(Entity entity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Entity> list = entityMapper.selectAll(entity);
        return PageInfo.of(list);
    }

    public void addbyname(String source) {
        Entity entity =new Entity();
        entity.initializeDefaultValues();
        entity.setEntity(source);

        entityMapper.insertwithname(entity);
    }
}