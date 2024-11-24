package com.example.mapper;

import com.example.entity.Bookcompnet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作bookcompnet相关数据接口
 */
@Mapper
public interface BookcompnetMapper {

    /**
     * 新增
     */
    int insert(Bookcompnet bookcompnet);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Bookcompnet bookcompnet);

    /**
     * 根据ID查询
     */
    Bookcompnet selectById(Integer id);

    /**
     * 查询所有
     */
    List<Bookcompnet> selectAll(Bookcompnet bookcompnet);

    List<Bookcompnet> selectBybookId(Integer bookid);
}