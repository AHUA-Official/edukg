package com.example.controller;

import com.example.common.Result;
import com.example.entity.Bookcompnet;
import com.example.service.BookcompnetService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 阅读评论表前端操作接口
 **/
@RestController
@RequestMapping("/bookcompnet")
public class BookcompnetController {

    @Resource
    private BookcompnetService bookcompnetService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Bookcompnet bookcompnet) {
        bookcompnetService.add(bookcompnet);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        bookcompnetService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        bookcompnetService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Bookcompnet bookcompnet) {
        bookcompnetService.updateById(bookcompnet);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Bookcompnet bookcompnet = bookcompnetService.selectById(id);
        return Result.success(bookcompnet);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Bookcompnet bookcompnet) {
        List<Bookcompnet> list = bookcompnetService.selectAll(bookcompnet);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Bookcompnet bookcompnet,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Bookcompnet> page = bookcompnetService.selectPage(bookcompnet, pageNum, pageSize);
        return Result.success(page);
    }

}