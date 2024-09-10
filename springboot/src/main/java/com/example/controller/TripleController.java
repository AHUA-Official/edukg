package com.example.controller;

import com.example.common.Result;
import com.example.entity.Triple;
import com.example.service.TripleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 三元组表前端操作接口
 **/
@RestController
@RequestMapping("/Triple")
public class TripleController {

    @Resource
    private TripleService TripleService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Triple Triple) {
        TripleService.add(Triple);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        TripleService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        TripleService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Triple Triple) {
        TripleService.updateById(Triple);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Triple Triple = TripleService.selectById(id);
        return Result.success(Triple);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Triple Triple) {
        List<Triple> list = TripleService.selectAll(Triple);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Triple Triple,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Triple> page = TripleService.selectPage(Triple, pageNum, pageSize);
        return Result.success(page);
    }

}