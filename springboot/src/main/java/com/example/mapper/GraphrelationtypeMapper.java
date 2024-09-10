package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Graphrelationtype;

import java.util.List;

public interface GraphrelationtypeMapper extends BaseMapper<Graphrelationtype> {

    /**
      * 查询所有
    */
    List<Graphrelationtype> selectAll(Graphrelationtype graphrelationtype);

    /**
      * 根据ID查询
    */
    Graphrelationtype selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}