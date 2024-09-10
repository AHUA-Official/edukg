package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Graphnode;
import com.example.service.GraphnodeService;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
*  描述：知识图谱节点表相关接口
*/
@RestController
@RequestMapping("/graphnode")
public class GraphnodeController {

    @Resource
	GraphnodeService graphnodeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Graphnode graphnode) {

        graphnodeService.add(graphnode);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        graphnodeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        graphnodeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Graphnode graphnode) {

        graphnodeService.updateById(graphnode);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Graphnode graphnode = graphnodeService.selectById(id);
        return Result.success(graphnode);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Graphnode graphnode) {
        List<Graphnode> list = graphnodeService.selectAll(graphnode);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Graphnode graphnode,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Graphnode> pageInfo = graphnodeService.selectPage(graphnode, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			graphnodeService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Graphnode> all = graphnodeService.selectAll(new Graphnode());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("节点id", null);
			row.put("节点类型", null);
			row.put("neo4j标识", null);
			row.put("节点属性", null);
			row.put("文档知识支撑", null);
			row.put("创建时间", null);
			row.put("更新时间", null);
			row.put("被删除了吗", null);
			list.add(row);
		} else {
			for (Graphnode graphnode : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("节点id", graphnode.getNodeid());
				row.put("节点类型", graphnode.getNodetype());
				row.put("neo4j标识", graphnode.getGraphnodeid());
				row.put("节点属性", graphnode.getPropertiesjson());
				row.put("文档知识支撑", graphnode.getDocmennt());
				row.put("创建时间", graphnode.getCreatetime());
				row.put("更新时间", graphnode.getUpdateat());
				row.put("被删除了吗", graphnode.getIsdel());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=graphnodeInfoExcel.xlsx");
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
		List<Graphnode> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Graphnode.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Graphnode info : infoList) {
				try {
					graphnodeService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
