package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Material;

import java.util.List;

public interface MaterialMapper extends BaseMapper<Material> {

    /**
      * 查询所有
    */
    List<Material> selectAll(Material material);

    /**
      * 根据ID查询
    */
    Material selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}