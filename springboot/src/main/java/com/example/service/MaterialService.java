package com.example.service;

import com.example.entity.Material;
import com.example.mapper.MaterialMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaterialService {

    @Resource
    private MaterialMapper materialMapper;

    /**
     * 新增
     */
    public void add(Material material) {
        materialMapper.insert(material);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        materialMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            materialMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Material material) {
        materialMapper.updateById(material);
    }

    /**
     * 根据ID查询
     */
    public Material selectById(Integer id) {
        return materialMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Material> selectAll(Material material) {
        return materialMapper.selectAll(material);
    }

    /**
     * 分页查询
     */
    public PageInfo<Material> selectPage(Material material, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Material> list = this.selectAll(material);

        return PageInfo.of(list);
    }

}