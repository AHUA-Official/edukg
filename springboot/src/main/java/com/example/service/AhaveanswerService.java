package com.example.service;

import com.example.entity.Ahaveanswer;
import com.example.mapper.AhaveanswerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 已经有了答案的表业务处理
 **/
@Service
public class AhaveanswerService {

    @Resource
    private AhaveanswerMapper ahaveanswerMapper;

    /**
     * 新增
     */
    public void add(Ahaveanswer ahaveanswer) {
        ahaveanswerMapper.insert(ahaveanswer);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ahaveanswerMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ahaveanswerMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Ahaveanswer ahaveanswer) {
        ahaveanswerMapper.updateById(ahaveanswer);
    }

    /**
     * 根据ID查询
     */
    public Ahaveanswer selectById(Integer id) {
        return ahaveanswerMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Ahaveanswer> selectAll(Ahaveanswer ahaveanswer) {
        return ahaveanswerMapper.selectAll(ahaveanswer);
    }

    /**
     * 分页查询
     */
    public PageInfo<Ahaveanswer> selectPage(Ahaveanswer ahaveanswer, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Ahaveanswer> list = ahaveanswerMapper.selectAll(ahaveanswer);
        return PageInfo.of(list);
    }

    public boolean updateAnswerById(String putid, String answer) {
        try {
            // 调用MyBatis的Mapper或JpaRepository来更新answer字段
            // 以下是一个伪代码示例，您需要根据实际的ORM工具来实现
            int updatedRows = ahaveanswerMapper.updateAnswer(putid, answer);

            // 检查更新的行数是否为1（表示只更新了一行）
            return updatedRows == 1;
        } catch (Exception e) {
            // 处理可能的异常，例如记录不存在或数据库错误
            e.printStackTrace();
            return false;
        }

    }
}