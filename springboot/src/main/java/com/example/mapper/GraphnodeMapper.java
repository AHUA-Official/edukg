package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Graphnode;

import java.util.List;

public interface GraphnodeMapper extends BaseMapper<Graphnode> {

    /**
      * 查询所有
    */
    List<Graphnode> selectAll(Graphnode graphnode);

    /**
      * 根据ID查询
    */
    Graphnode selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}