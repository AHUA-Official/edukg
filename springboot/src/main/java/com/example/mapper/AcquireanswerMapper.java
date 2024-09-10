package com.example.mapper;

import com.example.entity.Acquireanswer;
import java.util.List;

/**
 * 操作acquireanswer相关数据接口
 */
public interface AcquireanswerMapper {

    /**
     * 新增
     */
    int insert(Acquireanswer acquireanswer);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Acquireanswer acquireanswer);

    /**
     * 根据ID查询
     */
    Acquireanswer selectById(Integer id);

    /**
     * 查询所有
     */
    List<Acquireanswer> selectAll(Acquireanswer acquireanswer);

    List<Acquireanswer> selectBygenstatus();
}