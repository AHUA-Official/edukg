package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Messagechat;

import java.util.List;

public interface MessagechatMapper extends BaseMapper<Messagechat> {

    /**
      * 查询所有
    */
    List<Messagechat> selectAll(Messagechat messagechat);

    /**
      * 根据ID查询
    */
    Messagechat selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}