package com.example.service;

import com.example.entity.TblMessageTpm;
import com.example.mapper.TblMessageTpmMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 消息模板表业务处理
 **/
@Service
public class TblMessageTpmService {

    @Resource
    private TblMessageTpmMapper tblMessageTpmMapper;

    /**
     * 新增
     */
    public void add(TblMessageTpm tblMessageTpm) {
        tblMessageTpmMapper.insert(tblMessageTpm);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        tblMessageTpmMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            tblMessageTpmMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(TblMessageTpm tblMessageTpm) {
        tblMessageTpmMapper.updateById(tblMessageTpm);
    }

    /**
     * 根据ID查询
     */
    public TblMessageTpm selectById(Integer id) {
        return tblMessageTpmMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<TblMessageTpm> selectAll(TblMessageTpm tblMessageTpm) {
        return tblMessageTpmMapper.selectAll(tblMessageTpm);
    }

    /**
     * 分页查询
     */
    public PageInfo<TblMessageTpm> selectPage(TblMessageTpm tblMessageTpm, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TblMessageTpm> list = tblMessageTpmMapper.selectAll(tblMessageTpm);
        return PageInfo.of(list);
    }

}