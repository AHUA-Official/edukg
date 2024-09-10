package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Graphnodetype;

import java.util.List;

public interface GraphnodetypeMapper extends BaseMapper<Graphnodetype> {

    /**
      * 查询所有
    */
    List<Graphnodetype> selectAll(Graphnodetype graphnodetype);

    /**
      * 根据ID查询
    */
    Graphnodetype selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}