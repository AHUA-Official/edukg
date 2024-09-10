package com.example.controller;

import com.example.common.Result;
import com.example.entity.Entity;
import com.example.entity.Triple;
import com.example.service.EntityService;
import com.example.service.TripleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/triple")
public class FurinaTripleController {


    @Resource
    private com.example.service.TripleService TripleService;
    @Resource
    private com.example.service.EntityService  EntityService;

//  triple   表的设计

    //json    3个字段    String
    // source
    //（主语实体）
    //target
    //（宾语实体）
    //edge
    //（关系）
    @PostMapping("/add")
    public Result add(@RequestBody Map<String, String> jsonMap) {
        String source = jsonMap.get("source");
        String target = jsonMap.get("target");
        String edge = jsonMap.get("edge");
        //  调用EntityService的selectBy方法获得传入source 带入实体    然后不为空   就获得id    为空  就调用add再获取id
        //  调用EntityService的selectBy方法获得传入 target带入实体    然后不为空   就获得id    为空  就调用add再获取id
        //现在构建 Triple实体   调用TripleService的add

// 确保 source 实体存在
        ensureEntityExists(source);
        Integer sourceId = EntityService.selectByEntity(source).getId();

// 确保 target 实体存在
        ensureEntityExists(target);
        Integer targetId = EntityService.selectByEntity(target).getId();
        // 调用 EntityService 查找或添加 source 实体

        // 构建 Triple 实体
        Triple my2012triple = new Triple();
        my2012triple.setSubjectid(sourceId);
        my2012triple.setTargetid(targetId);
        my2012triple.setEdge(edge);
        my2012triple.setTarget(target);
        my2012triple.setSubject(source);

        // 调用 TripleService 的 add 方法
       TripleService.add(my2012triple);

        return Result.success();
    }

    public void ensureEntityExists(String entityName) {
        Entity existingEntity = EntityService.selectByEntity(entityName);
        if (existingEntity == null) {

            EntityService.addbyname(entityName); // 确保调用添加方法
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        TripleService.deleteById(id);
        return Result.success();
    }


    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        TripleService.deleteBatch(ids);
        return Result.success();
    }


    @PutMapping("/update")
    public Result updateById(@RequestBody Triple Triple) {
        TripleService.updateById(Triple);
        return Result.success();
    }


    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Triple Triple = TripleService.selectById(id);
        return Result.success(Triple);
    }


    @GetMapping("/selectAll")
    public Result selectAll(Triple Triple) {
        List<Triple> list = TripleService.selectAll(Triple);
        return Result.success(list);
    }


    @GetMapping("/selectPage")
    public Result selectPage(Triple Triple,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Triple> page = TripleService.selectPage(Triple, pageNum, pageSize);
        return Result.success(page);
    }

}