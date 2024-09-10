package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Graphnodetype;
import com.example.service.GraphnodetypeService;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
*  描述：知识图谱节点类型表相关接口
*/
@RestController
@RequestMapping("/graphnodetype")
public class GraphnodetypeController {

    @Resource
	GraphnodetypeService graphnodetypeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Graphnodetype graphnodetype) {

        graphnodetypeService.add(graphnodetype);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        graphnodetypeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        graphnodetypeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Graphnodetype graphnodetype) {

        graphnodetypeService.updateById(graphnodetype);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Graphnodetype graphnodetype = graphnodetypeService.selectById(id);
        return Result.success(graphnodetype);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Graphnodetype graphnodetype) {
        List<Graphnodetype> list = graphnodetypeService.selectAll(graphnodetype);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Graphnodetype graphnodetype,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Graphnodetype> pageInfo = graphnodetypeService.selectPage(graphnodetype, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			graphnodetypeService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Graphnodetype> all = graphnodetypeService.selectAll(new Graphnodetype());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("节点类型id", null);
			row.put("节点类型名称", null);
			row.put("否具有层级结构", null);
			row.put("图标路径", null);
			row.put("创建时间", null);
			row.put("最后更新时间", null);
			row.put("是否被删除", null);
			list.add(row);
		} else {
			for (Graphnodetype graphnodetype : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("节点类型id", graphnodetype.getNodetypeid());
				row.put("节点类型名称", graphnodetype.getTypename());
				row.put("否具有层级结构", graphnodetype.getIshierarchical());
				row.put("图标路径", graphnodetype.getIconpath());
				row.put("创建时间", graphnodetype.getCreateat());
				row.put("最后更新时间", graphnodetype.getUpdatetime());
				row.put("是否被删除", graphnodetype.getIsdel());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=graphnodetypeInfoExcel.xlsx");
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
		List<Graphnodetype> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Graphnodetype.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Graphnodetype info : infoList) {
				try {
					graphnodetypeService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
