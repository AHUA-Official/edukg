package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Graphrelation;
import com.example.service.GraphrelationService;
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
*  描述：知识图谱关系表相关接口
*/
@RestController
@RequestMapping("/graphrelation")
public class GraphrelationController {

    @Resource
	GraphrelationService graphrelationService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Graphrelation graphrelation) {

        graphrelationService.add(graphrelation);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        graphrelationService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        graphrelationService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Graphrelation graphrelation) {

        graphrelationService.updateById(graphrelation);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Graphrelation graphrelation = graphrelationService.selectById(id);
        return Result.success(graphrelation);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Graphrelation graphrelation) {
        List<Graphrelation> list = graphrelationService.selectAll(graphrelation);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Graphrelation graphrelation,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Graphrelation> pageInfo = graphrelationService.selectPage(graphrelation, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			graphrelationService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Graphrelation> all = graphrelationService.selectAll(new Graphrelation());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("关系id", null);
			row.put("头id", null);
			row.put("尾巴id", null);
			row.put("关系类型", null);
			row.put("Neo4关系id", null);
			row.put("关系权重", null);
			row.put("创建时间", null);
			row.put("更新时间", null);
			row.put("被删除", null);
			list.add(row);
		} else {
			for (Graphrelation graphrelation : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("关系id", graphrelation.getRelationid());
				row.put("头id", graphrelation.getHeadid());
				row.put("尾巴id", graphrelation.getTailid());
				row.put("关系类型", graphrelation.getTypeid());
				row.put("Neo4关系id", graphrelation.getNeo4jid());
				row.put("关系权重", graphrelation.getWeight());
				row.put("创建时间", graphrelation.getCreateat());
				row.put("更新时间", graphrelation.getUpdateat());
				row.put("被删除", graphrelation.getIsdel());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=graphrelationInfoExcel.xlsx");
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
		List<Graphrelation> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Graphrelation.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Graphrelation info : infoList) {
				try {
					graphrelationService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
