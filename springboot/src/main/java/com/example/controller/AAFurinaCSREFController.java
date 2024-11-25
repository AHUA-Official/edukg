package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.AAFurinaCSREF;
import com.example.entity.Course;
import com.example.service.AAFurinaCSREFService;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/CSREF")
public class AAFurinaCSREFController {

    @Resource
    private AAFurinaCSREFService courseStudentrefService;

    @GetMapping("/selectAll")
    public Result selectAll( @RequestParam(value = "courseId", required = false) Integer courseId ) {
        System.out.println(courseId);
        AAFurinaCSREF courseStudentref =new AAFurinaCSREF();
        if (courseId != null) {
            courseStudentref.setCourseId(1);
            courseStudentref.setStudentId(null);
            courseStudentref.setCoursename(null);
            courseStudentref.setName(null);
            courseStudentref.setPhone(null);
        }



        List<AAFurinaCSREF> list = courseStudentrefService.selectbycourseid(courseId);
        System.out.println(list);
        return Result.success(list);
    }
    //导入一条数据   作为中间的Ref  需要做合规性的验证  传入的参数只有   "courseId": 28,
    //            "username": "2022090917008",
    //            "name": "张磊",
    //            "phone": "15282149533",
    //            "coursename": "Java编程"
    //首先需要在学生里面确定学生的存在性 查"username": "2022090917008",   如果不存在的话  默认创建这个学生  然后在课程表里面查 "courseId"
    //查不到的话 就不行   这两个合规之后  进行数据库插入


        @PostMapping("/add")
        public Result add(@RequestBody AAFurinaCSREF courseStudentref) {
            Map<String, Object> resultData = new HashMap<>();
            List<AAFurinaCSREF> failList = new ArrayList<>();
            List<String> genestory = new ArrayList<>();

            try {
                // 业务逻辑处理
               resultData = courseStudentrefService.add(courseStudentref);

                return Result.success(resultData);
            } catch (Exception e) {
                genestory.add("操作失败: " + e.getMessage());

                return Result.success(resultData);
            }

        }
    @PostMapping("/batchAdd")
    public Result batchAdd(@RequestBody List<AAFurinaCSREF> courseStudentrefs) {
        Map<String, Object> resultData = new HashMap<>();
        List<AAFurinaCSREF> failList = new ArrayList<>();
        List<String> genestory = new ArrayList<>();

        try {
            // 业务逻辑处理
            resultData = courseStudentrefService.batchAdd(courseStudentrefs);

            return Result.success(resultData);
        } catch (Exception e) {
            genestory.add("操作失败: " + e.getMessage());

            return Result.success(resultData);
        }


    }

    @GetMapping("/export")
    public void export(@RequestParam(required = true) Integer courseId,HttpServletResponse response) throws IOException {
        AAFurinaCSREF CSREF = new AAFurinaCSREF();
        CSREF.setStudentId(null);
        CSREF.setCourseId(courseId);
        CSREF.setUsername(null);
        CSREF.setName(null);
        CSREF.setPhone(null);
        CSREF.setCoursename(null);

        List<AAFurinaCSREF> all = courseStudentrefService.selectAll( CSREF);
        System.out.println("@@@@@@@");
        System.out.println(all);
        List<Map<String, Object>> list = new ArrayList<>(all.size());
        if (CollectionUtil.isEmpty(all)) {
            Map<String, Object> row = new LinkedHashMap<>();

            row.put("学生ID", null);
            row.put("课程ID", null);
            row.put("学号", null);
            row.put("姓名", null);
            row.put("电话", null);
            row.put("课程名", null);
            list.add(row);
        } else {
            for (AAFurinaCSREF csref : all) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("学生ID", csref.getStudentId());
                row.put("课程ID", csref.getCourseId());
                row.put("学号", csref.getUsername());
                row.put("姓名", csref.getName());
                row.put("电话", csref.getPhone());
                row.put("课程名", csref.getCoursename());
                list.add(row);
            }
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=courseStudentrefInfo.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }



    @GetMapping("/exportmb")
    public void exportmb(HttpServletResponse response) throws IOException {
        AAFurinaCSREF CSREF = new AAFurinaCSREF();
        CSREF.setStudentId(null);
        CSREF.setCourseId(null);
        CSREF.setUsername(null);
        CSREF.setName(null);
        CSREF.setPhone(null);
        CSREF.setCoursename(null);

        List<AAFurinaCSREF> all = courseStudentrefService.selectAll( CSREF);
        System.out.println("@@@@@@@");
        System.out.println(all);
        List<Map<String, Object>> list = new ArrayList<>(all.size());

            Map<String, Object> row = new LinkedHashMap<>();

            row.put("学生ID", null);
            row.put("课程ID", null);
            row.put("学号", null);
            row.put("姓名", null);
            row.put("电话", null);
            row.put("课程名", null);
            list.add(row);


        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=courseStudentrefInfo.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile file) throws IOException {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return batchAdd(null);
        }

        List<AAFurinaCSREF> infoList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 读取标题行
            Row headerRow = sheet.getRow(0);
            Map<Integer, String> columnMapping = new HashMap<>();
            for (Cell cell : headerRow) {
                String columnName = cell.getStringCellValue().trim();
                columnMapping.put(cell.getColumnIndex(), columnName);
            }

            // 遍历每一行（从第二行开始）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // 创建一个新的CourseInfo对象
                AAFurinaCSREF courseInfo = new AAFurinaCSREF();

                // 遍历每个单元格
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    String columnName = columnMapping.get(columnIndex);

                    switch (columnName) {
                        case "课程ID":
                            courseInfo.setCourseId(getCellValueAsInt(cell));
                            break;
                        case "学号":
                            courseInfo.setUsername(getCellValueAsString(cell));
                            break;
                        case "姓名":
                            courseInfo.setName(getCellValueAsString(cell));
                            break;
                        case "电话":
                            courseInfo.setPhone(getCellValueAsString(cell));
                            break;
                        case "课程名":
                            courseInfo.setCoursename(getCellValueAsString(cell));
                            break;
                    }
                }

                // 添加到列表中
                infoList.add(courseInfo);
            }
        }

        // 打印构造的数据
        for (AAFurinaCSREF info : infoList) {
            System.out.println(info.toString());
        }

        return batchAdd(infoList);

    }


    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // 辅助方法：将单元格值转换为整数
    private int getCellValueAsInt(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return Integer.parseInt(cell.getStringCellValue().trim());
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            case FORMULA:
                return (int) cell.getNumericCellValue();
            default:
                return 0;
        }
    }
            }





