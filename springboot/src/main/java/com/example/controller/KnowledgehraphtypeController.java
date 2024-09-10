package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Knowledgehraphtype;
import com.example.service.KnowledgehraphtypeService;
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
*  描述：知识图谱类型表相关接口
*/
@RestController
@RequestMapping("/knowledgehraphtype")
public class KnowledgehraphtypeController {

    @Resource
	KnowledgehraphtypeService knowledgehraphtypeService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Knowledgehraphtype knowledgehraphtype) {

        knowledgehraphtypeService.add(knowledgehraphtype);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        knowledgehraphtypeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        knowledgehraphtypeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Knowledgehraphtype knowledgehraphtype) {

        knowledgehraphtypeService.updateById(knowledgehraphtype);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Knowledgehraphtype knowledgehraphtype = knowledgehraphtypeService.selectById(id);
        return Result.success(knowledgehraphtype);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Knowledgehraphtype knowledgehraphtype) {
        List<Knowledgehraphtype> list = knowledgehraphtypeService.selectAll(knowledgehraphtype);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Knowledgehraphtype knowledgehraphtype,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Knowledgehraphtype> pageInfo = knowledgehraphtypeService.selectPage(knowledgehraphtype, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			knowledgehraphtypeService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Knowledgehraphtype> all = knowledgehraphtypeService.selectAll(new Knowledgehraphtype());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("图谱类型的唯一标识", null);
			row.put("类型名称", null);
			row.put("简短描述", null);
			row.put("知识图谱来源", null);
			row.put("关联资料", null);
			row.put("图谱类型的图标路径", null);
			row.put("创建时间", null);
			row.put("更新时间", null);
			row.put("是否删除", null);
			list.add(row);
		} else {
			for (Knowledgehraphtype knowledgehraphtype : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("图谱类型的唯一标识", knowledgehraphtype.getGraphtypeid());
				row.put("类型名称", knowledgehraphtype.getTypename());
				row.put("简短描述", knowledgehraphtype.getDescription());
				row.put("知识图谱来源", knowledgehraphtype.getSourceurl());
				row.put("关联资料", knowledgehraphtype.getLinkedmaterial());
				row.put("图谱类型的图标路径", knowledgehraphtype.getIconpath());
				row.put("创建时间", knowledgehraphtype.getCreatetime());
				row.put("更新时间", knowledgehraphtype.getUpdateat());
				row.put("是否删除", knowledgehraphtype.getIsdeleted());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=knowledgehraphtypeInfoExcel.xlsx");
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
		List<Knowledgehraphtype> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Knowledgehraphtype.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Knowledgehraphtype info : infoList) {
				try {
					knowledgehraphtypeService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
