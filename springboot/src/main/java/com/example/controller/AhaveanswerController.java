package com.example.controller;

import com.example.common.Result;
import com.example.entity.Ahaveanswer;
import com.example.service.AhaveanswerService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 已经有了答案的表前端操作接口
 **/
@RestController
@RequestMapping("/ahaveanswer")
public class AhaveanswerController {

    @Resource
    private AhaveanswerService ahaveanswerService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Ahaveanswer ahaveanswer) {
        ahaveanswerService.add(ahaveanswer);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        ahaveanswerService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ahaveanswerService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Ahaveanswer ahaveanswer) {
        ahaveanswerService.updateById(ahaveanswer);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Ahaveanswer ahaveanswer = ahaveanswerService.selectById(id);
        return Result.success(ahaveanswer);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Ahaveanswer ahaveanswer) {
        List<Ahaveanswer> list = ahaveanswerService.selectAll(ahaveanswer);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Ahaveanswer ahaveanswer,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Ahaveanswer> page = ahaveanswerService.selectPage(ahaveanswer, pageNum, pageSize);
        return Result.success(page);
    }

}