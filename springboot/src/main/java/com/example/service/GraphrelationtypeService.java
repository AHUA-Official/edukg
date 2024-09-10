package com.example.service;

import com.example.entity.Graphrelationtype;
import com.example.mapper.GraphrelationtypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GraphrelationtypeService {

    @Resource
    private GraphrelationtypeMapper graphrelationtypeMapper;

    /**
     * 新增
     */
    public void add(Graphrelationtype graphrelationtype) {
        graphrelationtypeMapper.insert(graphrelationtype);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        graphrelationtypeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            graphrelationtypeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Graphrelationtype graphrelationtype) {
        graphrelationtypeMapper.updateById(graphrelationtype);
    }

    /**
     * 根据ID查询
     */
    public Graphrelationtype selectById(Integer id) {
        return graphrelationtypeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Graphrelationtype> selectAll(Graphrelationtype graphrelationtype) {
        return graphrelationtypeMapper.selectAll(graphrelationtype);
    }

    /**
     * 分页查询
     */
    public PageInfo<Graphrelationtype> selectPage(Graphrelationtype graphrelationtype, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Graphrelationtype> list = this.selectAll(graphrelationtype);

        return PageInfo.of(list);
    }

}