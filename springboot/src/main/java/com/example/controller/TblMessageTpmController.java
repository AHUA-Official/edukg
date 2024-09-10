package com.example.controller;

import com.example.common.Result;
import com.example.entity.TblMessageTpm;
import com.example.service.TblMessageTpmService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 消息模板表前端操作接口
 **/
@RestController
@RequestMapping("/tblMessageTpm")
public class TblMessageTpmController {

    @Resource
    private TblMessageTpmService tblMessageTpmService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody TblMessageTpm tblMessageTpm) {
        tblMessageTpmService.add(tblMessageTpm);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        tblMessageTpmService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        tblMessageTpmService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody TblMessageTpm tblMessageTpm) {
        tblMessageTpmService.updateById(tblMessageTpm);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        TblMessageTpm tblMessageTpm = tblMessageTpmService.selectById(id);
        return Result.success(tblMessageTpm);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(TblMessageTpm tblMessageTpm) {
        List<TblMessageTpm> list = tblMessageTpmService.selectAll(tblMessageTpm);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(TblMessageTpm tblMessageTpm,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<TblMessageTpm> page = tblMessageTpmService.selectPage(tblMessageTpm, pageNum, pageSize);
        return Result.success(page);
    }

}