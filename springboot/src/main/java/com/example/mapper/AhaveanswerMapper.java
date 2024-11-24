package com.example.mapper;

import com.example.entity.Ahaveanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作ahaveanswer相关数据接口
 */
@Mapper
public interface AhaveanswerMapper {

    /**
     * 新增
     */
    int insert(Ahaveanswer ahaveanswer);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Ahaveanswer ahaveanswer);

    /**
     * 根据ID查询
     */
    Ahaveanswer selectById(Integer id);

    /**
     * 查询所有
     */
    List<Ahaveanswer> selectAll(Ahaveanswer ahaveanswer);

    int updateAnswer(String putid, String answer);
}