package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Graphrelation;

import java.util.List;

public interface GraphrelationMapper extends BaseMapper<Graphrelation> {

    /**
      * 查询所有
    */
    List<Graphrelation> selectAll(Graphrelation graphrelation);

    /**
      * 根据ID查询
    */
    Graphrelation selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}