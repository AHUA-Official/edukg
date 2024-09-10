package com.example.service;

import com.example.entity.Triple;
import com.example.mapper.TripleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 三元组表业务处理
 **/
@Service
public class TripleService {

    @Resource
    private TripleMapper TripleMapper;

    /**
     * 新增
     */
    public void add(Triple Triple) {
        TripleMapper.insert(Triple);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        TripleMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            TripleMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Triple Triple) {
        TripleMapper.updateById(Triple);
    }

    /**
     * 根据ID查询
     */
    public Triple selectById(Integer id) {
        return TripleMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Triple> selectAll(Triple Triple) {
        return TripleMapper.selectAll(Triple);
    }

    /**
     * 分页查询
     */
    public PageInfo<Triple> selectPage(Triple Triple, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Triple> list = TripleMapper.selectAll(Triple);
        return PageInfo.of(list);
    }

}