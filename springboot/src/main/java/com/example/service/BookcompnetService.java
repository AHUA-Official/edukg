package com.example.service;

import com.example.entity.Bookcompnet;
import com.example.mapper.BookcompnetMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 阅读评论表业务处理
 **/
@Service
public class BookcompnetService {

    @Resource
    private BookcompnetMapper bookcompnetMapper;

    /**
     * 新增
     */
    public void add(Bookcompnet bookcompnet) {
        bookcompnetMapper.insert(bookcompnet);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        bookcompnetMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            bookcompnetMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Bookcompnet bookcompnet) {
        bookcompnetMapper.updateById(bookcompnet);
    }

    /**
     * 根据ID查询
     */
    public Bookcompnet selectById(Integer id) {
        return bookcompnetMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Bookcompnet> selectAll(Bookcompnet bookcompnet) {
        return bookcompnetMapper.selectAll(bookcompnet);
    }

    /**
     * 分页查询
     */
    public PageInfo<Bookcompnet> selectPage(Bookcompnet bookcompnet, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Bookcompnet> list = bookcompnetMapper.selectAll(bookcompnet);
        return PageInfo.of(list);
    }

    public List<Bookcompnet> findByBookid(Integer bookid) {
        return bookcompnetMapper.selectBybookId(bookid);
    }
}