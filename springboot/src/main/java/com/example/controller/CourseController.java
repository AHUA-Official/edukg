package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;

import com.example.common.config.TokenUtils;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Account;
import com.example.entity.Course;
import com.example.exception.CustomException;
import com.example.service.CourseService;

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
import java.util.stream.Collectors;

/**
*  描述：课程相关接口
*/
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
	CourseService courseService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
		Account currentUser = TokenUtils.getCurrentUser();
		if (ObjectUtil.isEmpty(currentUser)) {
			throw new CustomException(ResultCodeEnum.USER_NOT_LOGIN);
		}
		if ("teacher".equals(currentUser.getRole())) {
			course.setTeacherId(String.valueOf(currentUser.getId()));
		}

        courseService.add(course);
        return Result.success();
    }
	@PostMapping("/del")
	public Result del(@RequestBody Map<String, Object> params) {
		String coursename = (String) params.get("coursename");
		String teacher = (String) params.get("teacherID");

		// 调用服务层的删除方法
		boolean isDeleted = courseService.deleteBynameandtaecher(coursename, teacher);
		if (isDeleted) {
			return Result.success();
		} else {
			return Result.error();
		}
	}

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        courseService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Course course) {

        courseService.updateById(course);
        return Result.success();
    }
	@GetMapping("/selectByTeacherId/{teacherId}")
	public Result selectByTeacherId(@PathVariable String teacherId) {
		List<Course> courses = courseService.selectByTeacherId(teacherId);
		return Result.success(courses);
	}
    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Course course = courseService.selectById(id);
        return Result.success(course);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Course course) {
        List<Course> list = courseService.selectAll();
        return Result.success(list);
    }
	@GetMapping("/selectbyteacherid")
	public Result selectbyteacherid(@RequestParam(value = "teacherId", required = false, defaultValue = "8") Integer teacherId) {
		List<Course> allCourses = courseService.selectAll(); // 假设这个方法返回所有课程

		// 使用 Stream API 过滤掉特定 teacher_id 的课程
		List<Course> filteredCourses = allCourses.stream()
				.filter(course -> !course.getTeacherId().equals(teacherId))
				.collect(Collectors.toList());

		return Result.success(filteredCourses);
	}


    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Course course,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Course> pageInfo = courseService.selectPage(course, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			courseService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Course> all = courseService.selectAll();
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("课程名", null);
			row.put("老师", null);
			row.put("学期", null);
			row.put("年份", null);
			row.put("学分", null);
			row.put("课程描述", null);
			row.put("容纳学生数量", null);
			row.put("现在的学生数量", null);
			row.put("上课教室", null);
			row.put("时间表", null);
			row.put("课程状态", null);
			list.add(row);
		} else {
			for (Course course : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("课程名", course.getCoursename());
				row.put("老师", course.getTeacher());
				row.put("学期", course.getSemester());
				row.put("年份", course.getYear());
				row.put("学分", course.getCredits());
				row.put("课程描述", course.getCoursedescription());
				row.put("容纳学生数量", course.getMaxstudent());
				row.put("现在的学生数量", course.getNowstudentNum());
				row.put("上课教室", course.getClassroom());
				row.put("时间表", course.getSchedule());
				row.put("课程状态", course.getCoursestatus());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=courseInfoExcel.xlsx");
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
		List<Course> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Course.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Course info : infoList) {
				try {
					courseService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
