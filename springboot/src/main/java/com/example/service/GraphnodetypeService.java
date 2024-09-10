package com.example.service;

import com.example.entity.Graphnodetype;
import com.example.mapper.GraphnodetypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GraphnodetypeService {

    @Resource
    private GraphnodetypeMapper graphnodetypeMapper;

    /**
     * 新增
     */
    public void add(Graphnodetype graphnodetype) {
        graphnodetypeMapper.insert(graphnodetype);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        graphnodetypeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            graphnodetypeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Graphnodetype graphnodetype) {
        graphnodetypeMapper.updateById(graphnodetype);
    }

    /**
     * 根据ID查询
     */
    public Graphnodetype selectById(Integer id) {
        return graphnodetypeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Graphnodetype> selectAll(Graphnodetype graphnodetype) {
        return graphnodetypeMapper.selectAll(graphnodetype);
    }

    /**
     * 分页查询
     */
    public PageInfo<Graphnodetype> selectPage(Graphnodetype graphnodetype, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Graphnodetype> list = this.selectAll(graphnodetype);

        return PageInfo.of(list);
    }

}