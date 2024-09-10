package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.example.common.Result;
import com.example.entity.KNLIST;
import com.example.service.KNLISTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
/**
 * 知识清单表前端操作接口
 **/
@RestController
@RequestMapping("/KNLIST")
public class FurinaKNListController {

    @Resource
    private KNLISTService KNLISTService;

    /**
     * 新增
     */
    @PostMapping("/add11111")
    public Result add1111111(@RequestBody KNLIST KNLIST) {
        KNLISTService.add(KNLIST);
        return Result.success();
    }


    @PostMapping("/add")
    public Result add(@RequestBody  String json) {
//        String userId = jsonMap.get("userId");
//        String courseName = (String) jsonMap.get("coursename");
//        String chaptername = (String) jsonMap.get("chaptername");
//        for (Map<String, Object> knowledgePoint : knowledgePointsList) {
//            // 从每个元素中获取name和detail
//            String name = (String) knowledgePoint.get("name");
//            Object detail = knowledgePoint.get("detail");
//
//
//    }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将JSON字符串反序列化为Map
            Map<String, Object> jsonMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});

            // 从Map中获取userId, courseName, chaptername
            Integer userId = objectMapper.convertValue(jsonMap.get("userId"), Integer.class);
            String courseName = (String) jsonMap.get("coursename");
            String chaptername = (String) jsonMap.get("chaptername");

            // 获取knowledgePoints数组
            List<Map<String, Object>> knowledgePointsList = objectMapper.convertValue(
                    jsonMap.get("knowledgePoints"),
                    new TypeReference<List<Map<String, Object>>>(){});

            // 处理knowledgePoints数组
            for (Map<String, Object> knowledgePoint : knowledgePointsList) {
                // 从每个元素中获取name和detail
                String name = (String) knowledgePoint.get("name");
                Object detail = knowledgePoint.get("detail");

                // 这里可以添加处理每个知识点的逻辑
            }

            String formattedKnowledgePointsJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(knowledgePointsList);
            System.out.println("Formatted knowledgePoints JSON:\n" + formattedKnowledgePointsJson);

            // 打印并格式化整个jsonMap
            String formattedJsonMap = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
            System.out.println("Formatted JSON Map:\n" + formattedJsonMap);

            saveJsonToFile(json, userId,courseName, chaptername);


} catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Result.success();
    }

    public static void saveJsonToFile(String json, int userId, String courseName, String chapterName) {
        // 构建文件名
        String fileName = "edukg" + userId + "A" + courseName.replace(" ", "_") + "A" + chapterName.replace(" ", "_") + ".json";

        String folderPath = "src/main/resources/jsons";

        // 确保jsons文件夹存在
        Path dirPath = Paths.get(folderPath);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath); // 创建目录
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建目录失败");
                return;
            }
        }

        // 完整的文件路径
        Path filePath = dirPath.resolve(fileName);

        // 使用ObjectMapper格式化JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            String prettyJson = objectMapper.writer(new DefaultPrettyPrinter()).writeValueAsString(json);

            // 写入文件
            writer.write(prettyJson);
            System.out.println("JSON数据已保存到: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JSON格式化或文件写入失败");
        }
    }
    @GetMapping("/selectALL")
    public ResponseEntity<String> getJsonData(@RequestParam("fileName") String fileName) {
        String filePath = "src/main/resources/jsons/" + fileName; // 构建文件路径
        try {
            String fileContent = readFileContent(filePath);

            if (fileContent != null && !fileContent.isEmpty()) {
                // 使用 fastjson 进行美化格式化
          //      String prettyJson = JSON.toJSONString(JSON.parse(fileContent), new PrettyFormatWriter());
                String prettyJson = JSON.toJSONString(JSON.parse(fileContent), true);
                return ResponseEntity.ok(prettyJson);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private String readFileContent(String filePath) throws IOException {
        // 读取文件内容
        byte[] fileContentBytes = Files.readAllBytes(Paths.get(filePath));
        return new String(fileContentBytes, StandardCharsets.UTF_8);
    }
}
////一个selectALL 接口  用好看的形式返回src/resource/jsons的一个json文件   额我的意思是   把json文件直接里面的内容拿出来返回一下
//
//    @GetMapping("/selectALL")
//    public ResponseEntity<String> getJsonData(String filePath) {
//        try {
//            // 读取文件内容
//            String fileContent = readFileContent(filePath);
//
//            if (fileContent != null && !fileContent.isEmpty()) {
//                // 格式化JSON字符串
//                String prettyJson = new ObjectMapper().writer(new DefaultPrettyPrinter()).writeValueAsString(fileContent);
//
//                // 返回格式化后的JSON字符串
//                return ResponseEntity.ok(prettyJson);
//            } else {
//                // 文件为空或不存在
//                return ResponseEntity.notFound().build();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // 读取文件时发生异常
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    /**
//     * 读取文件内容。
//     * @param filePath 文件路径
//     * @return 字符串形式的文件内容，如果发生错误或文件不存在则返回null
//     */
//    private String readFileContent(String filePath) {
//        //  String folderPath = "src/main/resources/jsons";
//        //
//        //        // 确保jsons文件夹存在
//        //        Path dirPath = Paths.get(folderPath);
//        //        if (!Files.exists(dirPath)) {
//        //            try {
//        //                Files.createDirectories(dirPath); // 创建目录
//        //            } catch (IOException e) {
//        //                e.printStackTrace();
//        //                System.out.println("创建目录失败");
//        //                return;
//        //            }
//        //        }
//        //
//        //        // 完整的文件路径
//        //        Path filePath = dirPath.resolve(fileName);
//       // Path path = Paths.get("src/main/resources", filePath);
//        Path dirPath = Paths.get("src/main/resources/jsons");
//        Path path = dirPath.resolve(filePath);
////        try {
////           String    mycontent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
////            return  mycontent;
////        } catch (IOException e) {
////            // 打印异常信息，返回null表示读取失败
////            e.printStackTrace();
////            return null;
////        }
//        // 使用ObjectMapper读取JSON文件
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            // 读取整个文件内容作为一个字符串
//            String fileContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
//
//            // 使用ObjectMapper将字符串反序列化为JSON对象
//            // 这里假设JSON的根是一个对象（Map）
////            Object jsonNode = objectMapper.readTree(fileContent);
////
////            // 使用DefaultPrettyPrinter格式化JSON对象
//            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
////            prettyPrinter.withoutSpacesInObjectEntries();
////            return objectMapper.writer(prettyPrinter).writeValueAsString(jsonNode);
//            com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(fileContent);
//            // 使用prettyPrinter进行美化格式化并返回
//            return objectMapper.writer(prettyPrinter).writeValueAsString(jsonNode);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
//


