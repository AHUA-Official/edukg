package com.example.service;

import com.example.entity.Graphrelation;
import com.example.mapper.GraphrelationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GraphrelationService {

    @Resource
    private GraphrelationMapper graphrelationMapper;

    /**
     * 新增
     */
    public void add(Graphrelation graphrelation) {
        graphrelationMapper.insert(graphrelation);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        graphrelationMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            graphrelationMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Graphrelation graphrelation) {
        graphrelationMapper.updateById(graphrelation);
    }

    /**
     * 根据ID查询
     */
    public Graphrelation selectById(Integer id) {
        return graphrelationMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Graphrelation> selectAll(Graphrelation graphrelation) {
        return graphrelationMapper.selectAll(graphrelation);
    }

    /**
     * 分页查询
     */
    public PageInfo<Graphrelation> selectPage(Graphrelation graphrelation, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Graphrelation> list = this.selectAll(graphrelation);

        return PageInfo.of(list);
    }

}