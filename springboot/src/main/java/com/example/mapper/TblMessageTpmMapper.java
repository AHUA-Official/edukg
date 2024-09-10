package com.example.mapper;

import com.example.entity.TblMessageTpm;
import java.util.List;

/**
 * 操作tbl_message_tpm相关数据接口
 */
public interface TblMessageTpmMapper {

    /**
     * 新增
     */
    int insert(TblMessageTpm tblMessageTpm);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(TblMessageTpm tblMessageTpm);

    /**
     * 根据ID查询
     */
    TblMessageTpm selectById(Integer id);

    /**
     * 查询所有
     */
    List<TblMessageTpm> selectAll(TblMessageTpm tblMessageTpm);

}