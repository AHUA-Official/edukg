package com.example.service;

import com.example.entity.KNLIST;
import com.example.mapper.KNLISTMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 知识清单表业务处理
 **/
@Service
public class KNLISTService {

    @Resource
    private KNLISTMapper KNLISTMapper;

    /**
     * 新增
     */
    public void add(KNLIST KNLIST) {
        KNLISTMapper.insert(KNLIST);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        KNLISTMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            KNLISTMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(KNLIST KNLIST) {
        KNLISTMapper.updateById(KNLIST);
    }

    /**
     * 根据ID查询
     */
    public KNLIST selectById(Integer id) {
        return KNLISTMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<KNLIST> selectAll(KNLIST KNLIST) {
        return KNLISTMapper.selectAll(KNLIST);
    }

    /**
     * 分页查询
     */
    public PageInfo<KNLIST> selectPage(KNLIST KNLIST, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<KNLIST> list = KNLISTMapper.selectAll(KNLIST);
        return PageInfo.of(list);
    }

}