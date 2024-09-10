package com.example.service;

import com.example.entity.Knowledgehraphtype;
import com.example.mapper.KnowledgehraphtypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnowledgehraphtypeService {

    @Resource
    private KnowledgehraphtypeMapper knowledgehraphtypeMapper;

    /**
     * 新增
     */
    public void add(Knowledgehraphtype knowledgehraphtype) {
        knowledgehraphtypeMapper.insert(knowledgehraphtype);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        knowledgehraphtypeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            knowledgehraphtypeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Knowledgehraphtype knowledgehraphtype) {
        knowledgehraphtypeMapper.updateById(knowledgehraphtype);
    }

    /**
     * 根据ID查询
     */
    public Knowledgehraphtype selectById(Integer id) {
        return knowledgehraphtypeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Knowledgehraphtype> selectAll(Knowledgehraphtype knowledgehraphtype) {
        return knowledgehraphtypeMapper.selectAll(knowledgehraphtype);
    }

    /**
     * 分页查询
     */
    public PageInfo<Knowledgehraphtype> selectPage(Knowledgehraphtype knowledgehraphtype, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Knowledgehraphtype> list = this.selectAll(knowledgehraphtype);

        return PageInfo.of(list);
    }

}