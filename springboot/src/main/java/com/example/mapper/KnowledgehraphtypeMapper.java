package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Knowledgehraphtype;

import java.util.List;

public interface KnowledgehraphtypeMapper extends BaseMapper<Knowledgehraphtype> {

    /**
      * 查询所有
    */
    List<Knowledgehraphtype> selectAll(Knowledgehraphtype knowledgehraphtype);

    /**
      * 根据ID查询
    */
    Knowledgehraphtype selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}