package com.example.controller;

import com.example.common.Result;
import com.example.entity.Entity;
import com.example.service.EntityService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 知识实体表前端操作接口
 **/
@RestController
@RequestMapping("/entity")
public class EntityController {

    @Resource
    private EntityService entityService;

    /**
     * 新增
     */
    @PostMapping("/add111")
    public Result add1111(@RequestBody Entity entity) {
        entityService.add(entity);
        return Result.success();
    }
    @PostMapping("/add")
    public Result add(@RequestBody Entity entity) {
        Entity existingEntity = entityService.selectByEntity(entity.getEntity());

        // 如果存在，返回提示信息
        if (existingEntity != null) {
            return Result.error("400","Entity already exists.");
        }

        // 如果不存在，添加实体
        entityService.add(entity);
        return Result.success("Entity added successfully.");
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        entityService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        entityService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Entity entity) {
        entityService.updateById(entity);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Entity entity = entityService.selectById(id);
        return Result.success(entity);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Entity entity) {
        List<Entity> list = entityService.selectAll(entity);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Entity entity,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Entity> page = entityService.selectPage(entity, pageNum, pageSize);
        return Result.success(page);
    }

}