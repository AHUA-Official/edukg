package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Graphrelationtype;
import com.example.service.GraphrelationtypeService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
*  描述：知识图谱关系类型表相关接口
*/
@RestController
@RequestMapping("/graphrelationtype")
public class GraphrelationtypeController {

    @Resource
	GraphrelationtypeService graphrelationtypeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Graphrelationtype graphrelationtype) {

        graphrelationtypeService.add(graphrelationtype);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        graphrelationtypeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        graphrelationtypeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Graphrelationtype graphrelationtype) {

        graphrelationtypeService.updateById(graphrelationtype);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Graphrelationtype graphrelationtype = graphrelationtypeService.selectById(id);
        return Result.success(graphrelationtype);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Graphrelationtype graphrelationtype) {
        List<Graphrelationtype> list = graphrelationtypeService.selectAll(graphrelationtype);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Graphrelationtype graphrelationtype,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Graphrelationtype> pageInfo = graphrelationtypeService.selectPage(graphrelationtype, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			graphrelationtypeService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Graphrelationtype> all = graphrelationtypeService.selectAll(new Graphrelationtype());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("关系类型id", null);
			row.put("关系类型的名字", null);
			row.put("关系方向性", null);
			row.put("权重的属性", null);
			list.add(row);
		} else {
			for (Graphrelationtype graphrelationtype : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("关系类型id", graphrelationtype.getRelationtypeid());
				row.put("关系类型的名字", graphrelationtype.getRelationtypename());
				row.put("关系方向性", graphrelationtype.getBidirectional());
				row.put("权重的属性", graphrelationtype.getWeight());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=graphrelationtypeInfoExcel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
		IoUtil.close(System.out);
	}

	/**
	 * 描述：通过excel批量导入
	 */
	@PostMapping("/upload")
	public Result upload(MultipartFile file) throws IOException {
		List<Graphrelationtype> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Graphrelationtype.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Graphrelationtype info : infoList) {
				try {
					graphrelationtypeService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
