package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Material;
import com.example.service.MaterialService;
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
*  描述：课程资料相关接口
*/
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Resource
	MaterialService materialService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Material material) {

        materialService.add(material);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        materialService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        materialService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Material material) {

        materialService.updateById(material);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Material material = materialService.selectById(id);
        return Result.success(material);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Material material) {
        List<Material> list = materialService.selectAll(material);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Material material,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Material> pageInfo = materialService.selectPage(material, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			materialService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Material> all = materialService.selectAll(new Material());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("课程", null);
			row.put("资料名", null);
			row.put("资料简介", null);
			row.put("资料路径", null);
			row.put("上传人", null);
			row.put("上传时间", null);
			row.put("资料类型", null);
			row.put("资料标签", null);
			row.put("资料版本", null);
			row.put("资料用途", null);
			list.add(row);
		} else {
			for (Material material : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("课程", material.getCourse());
				row.put("资料名", material.getDocname());
				row.put("资料简介", material.getDocsmary());
				row.put("资料路径", material.getDocpath());
				row.put("上传人", material.getUplodeedby());
				row.put("上传时间", material.getUploadedtime());
				row.put("资料类型", material.getContentype());
				row.put("资料标签", material.getDoctag());
				row.put("资料版本", material.getDocversion());
				row.put("资料用途", material.getDocpurpose());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=materialInfoExcel.xlsx");
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
		List<Material> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Material.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Material info : infoList) {
				try {
					materialService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
