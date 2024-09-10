package com.example.service;

import com.example.entity.Booknext;
import com.example.mapper.BooknextMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 课程资料业务处理
 **/
@Service
public class BooknextService {

    @Resource
    private BooknextMapper booknextMapper;

    /**
     * 新增
     */
    public void add(Booknext booknext) {
        booknextMapper.insert(booknext);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        booknextMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            booknextMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Booknext booknext) {
        booknextMapper.updateById(booknext);
    }

    /**
     * 根据ID查询
     */
    public Booknext selectById(Integer id) {
        return booknextMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Booknext> selectAll(Booknext booknext) {
        return booknextMapper.selectAll(booknext);
    }

    /**
     * 分页查询
     */
    public PageInfo<Booknext> selectPage(Booknext booknext, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Booknext> list = booknextMapper.selectAll(booknext);
        return PageInfo.of(list);
    }


    public List<Booknext> selectBycourseId(Integer id) {
        List<Booknext> list = booknextMapper.selectbycourseid(id);
        return  list;
    }
}