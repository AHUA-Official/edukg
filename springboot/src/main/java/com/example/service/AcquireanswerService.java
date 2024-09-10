package com.example.service;

import com.example.entity.Acquireanswer;
import com.example.mapper.AcquireanswerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 需要回答答案的表业务处理
 **/
@Service
public class AcquireanswerService {

    @Resource
    private AcquireanswerMapper acquireanswerMapper;

    /**
     * 新增
     */
    public void add(Acquireanswer acquireanswer) {
        acquireanswerMapper.insert(acquireanswer);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        acquireanswerMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            acquireanswerMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Acquireanswer acquireanswer) {
        acquireanswerMapper.updateById(acquireanswer);
    }

    /**
     * 根据ID查询
     */
    public Acquireanswer selectById(Integer id) {
        return acquireanswerMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Acquireanswer> selectAll(Acquireanswer acquireanswer) {
        return acquireanswerMapper.selectAll(acquireanswer);
    }

    /**
     * 分页查询
     */
    public PageInfo<Acquireanswer> selectPage(Acquireanswer acquireanswer, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Acquireanswer> list = acquireanswerMapper.selectAll(acquireanswer);
        return PageInfo.of(list);
    }

    public List<Acquireanswer> selectBygenStatus() {
        List<Acquireanswer> list = acquireanswerMapper.selectBygenstatus();
        return  list;
    }
}