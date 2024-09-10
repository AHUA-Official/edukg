package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Student;
import com.example.service.StudentService;
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
*  描述：学生相关接口
*/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentService studentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Student student) {

        studentService.add(student);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        studentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Student student) {

        studentService.updateById(student);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Student student = studentService.selectById(id);
        return Result.success(student);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Student student) {
        List<Student> list = studentService.selectAll(student);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Student student,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Student> pageInfo = studentService.selectPage(student, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			studentService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Student> all = studentService.selectAll(new Student());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("账号", null);
			row.put("名称", null);
			row.put("手机", null);
			row.put("邮箱", null);
			row.put("更新时间", null);
			row.put("创建时间", null);
			row.put("逻辑删除", null);
			row.put("可用登录方式", null);
			row.put("专业", null);
			list.add(row);
		} else {
			for (Student student : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("账号", student.getUsername());
				row.put("名称", student.getName());
				row.put("手机", student.getPhone());
				row.put("邮箱", student.getEmail());
				row.put("更新时间", student.getUpdatedAt());
				row.put("创建时间", student.getCreateAt());
				row.put("逻辑删除", student.getDeleted());
				row.put("可用登录方式", student.getLogintype());
				row.put("专业", student.getMajor());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=studentInfoExcel.xlsx");
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
		List<Student> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Student.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Student info : infoList) {
				try {
					studentService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
