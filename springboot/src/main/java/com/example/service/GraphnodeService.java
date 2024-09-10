package com.example.service;

import com.example.entity.Graphnode;
import com.example.mapper.GraphnodeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GraphnodeService {

    @Resource
    private GraphnodeMapper graphnodeMapper;

    /**
     * 新增
     */
    public void add(Graphnode graphnode) {
        graphnodeMapper.insert(graphnode);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        graphnodeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            graphnodeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Graphnode graphnode) {
        graphnodeMapper.updateById(graphnode);
    }

    /**
     * 根据ID查询
     */
    public Graphnode selectById(Integer id) {
        return graphnodeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Graphnode> selectAll(Graphnode graphnode) {
        return graphnodeMapper.selectAll(graphnode);
    }

    /**
     * 分页查询
     */
    public PageInfo<Graphnode> selectPage(Graphnode graphnode, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Graphnode> list = this.selectAll(graphnode);

        return PageInfo.of(list);
    }

}