package com.example.controller;

import com.example.common.Result;
import com.example.entity.Booknext;
import com.example.service.BooknextService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 课程资料前端操作接口
 **/
@RestController
@RequestMapping("/booknext")
public class BooknextController {

    @Resource
    private BooknextService booknextService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Booknext booknext) {
        booknextService.add(booknext);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        booknextService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        booknextService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Booknext booknext) {
        booknextService.updateById(booknext);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Booknext booknext = booknextService.selectById(id);
        return Result.success(booknext);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Booknext booknext) {
        List<Booknext> list = booknextService.selectAll(booknext);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Booknext booknext,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Booknext> page = booknextService.selectPage(booknext, pageNum, pageSize);
        return Result.success(page);
    }

}