package com.example.controller;

import com.example.common.Result;
import com.example.entity.Acquireanswer;
import com.example.service.AcquireanswerService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 需要回答答案的表前端操作接口
 **/
@RestController
@RequestMapping("/acquireanswer")
public class AcquireanswerController {

    @Resource
    private AcquireanswerService acquireanswerService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Acquireanswer acquireanswer) {
        acquireanswerService.add(acquireanswer);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        acquireanswerService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        acquireanswerService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Acquireanswer acquireanswer) {
        acquireanswerService.updateById(acquireanswer);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Acquireanswer acquireanswer = acquireanswerService.selectById(id);
        return Result.success(acquireanswer);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Acquireanswer acquireanswer) {
        List<Acquireanswer> list = acquireanswerService.selectAll(acquireanswer);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Acquireanswer acquireanswer,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Acquireanswer> page = acquireanswerService.selectPage(acquireanswer, pageNum, pageSize);
        return Result.success(page);
    }

}