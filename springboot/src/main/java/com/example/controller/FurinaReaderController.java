package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import com.example.entity.Booknext;
import com.example.entity.Entity;
import com.example.entity.Triple;
import com.example.service.BooknextService;
import com.example.service.EntityService;
import com.example.service.TripleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
@RestController
@RequestMapping("/reader")
public class FurinaReaderController {
    private static final String filePath = System.getProperty("user.dir") + "/files/";

    @Value("${server.port:8099}")
    private String port;

    @Value("${ip:localhost}")
    private String ip;

    @Resource
    private com.example.service.TripleService TripleService;
    @Resource
    private com.example.service.EntityService  EntityService;
    @Resource
    private BooknextService booknextService;

    @PostMapping("/addbooks")
    public Result add(@RequestPart("jsonMap") String jsonMap,  @RequestPart("file") MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMapObject;

        try {
            jsonMapObject = objectMapper.readValue(jsonMap, new TypeReference<Map<String, String>>(){});
        } catch (Exception e) {
            return Result.error("200","格式cuow");
        }

        Booknext booknext = new Booknext();
        booknext.setDocname((String) jsonMapObject.get("docname"));
        booknext.setDocmary((String) jsonMapObject.get("docmary"));
        booknext.setUploadone((String) jsonMapObject.get("uploadone"));
        booknext.setCourseid((String) jsonMapObject.get("courseid").toString());
        booknext.setCoursename((String) jsonMapObject.get("coursename"));

        String flag;
        synchronized (FileController.class) {
            flag = System.currentTimeMillis() + "";
            ThreadUtil.sleep(1L);
        }
        String fileName = file.getOriginalFilename();
        try {
            if (!FileUtil.isDirectory(filePath)) {
                FileUtil.mkdir(filePath);
            }
            // 文件存储形式：时间戳-文件名
            FileUtil.writeBytes(file.getBytes(), filePath  + fileName);  // ***/manager/files/1697438073596-avatar.png
            System.out.println(fileName + "--上传成功");

        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        String http = "http://" + ip + ":" + port + "/files/";

        // 调用文件上传逻辑
      //  String fileSavePath = saveFile(file);

        // 将文件保存路径设置到booknext对象，并重新保存以包含文件信息
        booknext.setDocpath(filePath);
        booknext.setDocname(fileName);


//
//        {  "docname": "示例文档",
//                "docmary": "这是一个示例文档的简介",
//                "uploadone": "上传者姓名",
//                "courseid": 101,
//                "coursename": "示例课程"
//        }
//        //  还需要在这个里面实现一个接受上传的文件的功能     把上传的文件原样存储在springboot工程的/resourcse的books下面
//        //之后用一个函数来初始化booknext   这样来完成这样的业务逻辑    用户上传对应课程的资料
//
//////参考资料
////        @RestController
////        @RequestMapping("/files")
////        public class FileController {
////
////            // 文件上传存储路径
////            private static final String filePath = System.getProperty("user.dir") + "/files/";
////
////            @Value("${server.port:9090}")
////            private String port;
////
////            @Value("${ip:localhost}")
////            private String ip;
////
////            /**
////             * 文件上传
////             */
////            @PostMapping("/upload")
////            public Result upload(MultipartFile file) {
////                String flag;
////                synchronized (com.example.controller.FileController.class) {
////                    flag = System.currentTimeMillis() + "";
////                    ThreadUtil.sleep(1L);
////                }
////                String fileName = file.getOriginalFilename();
////                try {
////                    if (!FileUtil.isDirectory(filePath)) {
////                        FileUtil.mkdir(filePath);
////                    }
////                    // 文件存储形式：时间戳-文件名
////                    FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);  // ***/manager/files/1697438073596-avatar.png
////                    System.out.println(fileName + "--上传成功");
////
////                } catch (Exception e) {
////                    System.err.println(fileName + "--文件上传失败");
////                }
////                String http = "http://" + ip + ":" + port + "/files/";
////                return Result.success(http + flag + "-" + fileName);  //  http://localhost:9090/files/1697438073596-avatar.png
////            }
////
////
////            /**
////             * 获取文件
////             *
////             * @param flag
////             * @param response
////             */
////            @GetMapping("/{flag}")   //  1697438073596-avatar.png
////            public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
////                OutputStream os;
////                try {
////                    if (StrUtil.isNotEmpty(flag)) {
////                        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
////                        response.setContentType("application/octet-stream");
////                        byte[] bytes = FileUtil.readBytes(filePath + flag);
////                        os = response.getOutputStream();
////                        os.write(bytes);
////                        os.flush();
////                        os.close();
////                    }
////                } catch (Exception e) {
////                    System.out.println("文件下载失败");
////                }
////            }
////
//
//            Booknext booknext = new Booknext();
////  编写一个函数对booknext初始化
//
//            booknextService.add(booknext);
//
//        return Result.success();

        booknextService.add(booknext);
        return Result.success();

    }
    @PostMapping("/addbooks1")
    public Result add1(@RequestBody Map<String, String> jsonMap,
                      @RequestParam("file") MultipartFile file) throws IOException {

        Booknext booknext = new Booknext();
        booknext.setDocname(jsonMap.get("docname"));
        booknext.setDocmary(jsonMap.get("docmary"));
        booknext.setUploadone(jsonMap.get("uploadone"));
        booknext.setCourseid((jsonMap.get("courseid"))); // 转换为Integer类型
        booknext.setCoursename(jsonMap.get("coursename"));

        // 调用文件上传逻辑
        String fileSavePath = saveFile(file);

        // 将文件保存路径设置到booknext对象
        booknext.setDocpath(fileSavePath);

        // 保存booknext对象到数据库
        booknextService.add(booknext);

        return Result.success("书籍资料和文件上传成功");
    }




    public void ensureEntityExists(String entityName) {
        Entity existingEntity = EntityService.selectByEntity(entityName);
        if (existingEntity == null) {

            EntityService.addbyname(entityName); // 确保调用添加方法
        }
    }
    @GetMapping("/selectById")
    public Result selectcourseId(@RequestParam("id") Integer id) {
        List<Booknext> booknext = booknextService.selectBycourseId(id);
        return Result.success(booknext);
    }

    @PostMapping("/getbooks")
    public void selectById(@RequestBody Map<String, String> jsonMap,HttpServletResponse response) {
//        Integer   id = Integer.parseInt(jsonMap.get("id"));
//        Booknext booknext = booknextService.selectById(id);
//        String filename  = booknext.getDocname();
//        String filepath   = booknext.getDocpath();
//        //根据filename和filepath获得文件  同时返还给用户对应的文件   这个咋做？
//        return Result.success(booknext);
        try {
            Integer id = Integer.parseInt(jsonMap.get("id"));
            Booknext booknext = booknextService.selectById(id);
            if (booknext != null && booknext.getDocpath() != null && booknext.getDocname() != null) {
                String filename = booknext.getDocname();
                String filea = booknext.getDocpath(); // 确保这是文件在服务器上的完整路径
                String filepath =filea+filename;

                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));

                // 读取文件并写入响应
                File file = new File(filepath);
                byte[] buffer = new byte[4096];
                try (InputStream is = new FileInputStream(file);
                     OutputStream os = response.getOutputStream()) {
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.flush();
                }
            } else {
                // 文件信息不存在，返回错误或提示信息
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            // 错误的id格式，返回错误信息
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (IOException e) {
            // 文件读取或写入过程中发生错误
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping("/getbook")
    public void selectBybookId(@RequestBody Map<String, String> jsonMap, HttpServletResponse response) {
        try {
            Integer id = Integer.parseInt(jsonMap.get("id"));
            Booknext booknext = booknextService.selectById(id);
            if (booknext != null && booknext.getDocpath() != null && booknext.getDocname() != null) {
                String filename = booknext.getDocname();
                String fileDir = booknext.getDocpath(); // 文件目录
                String filepath = fileDir + File.separator + filename; // 完整的文件路径

                // 根据文件后缀名设置Content-Type
                String contentType = getContentTypeFromExtension(filename);

                // 设置响应头信息
                response.setContentType(contentType);
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20"));

                // 读取文件并写入响应
                File file = new File(filepath);
                try (InputStream is = new FileInputStream(file);
                     OutputStream os = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.flush();
                }
            } else {
                // 文件信息不存在，返回错误或提示信息
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("File not found.");
            }
        } catch (NumberFormatException e) {
            // 错误的id格式，返回错误信息
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } catch (IOException e) {
            // 文件读取或写入过程中发生错误
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }

    // 根据文件扩展名获取MIME类型
    private String getContentTypeFromExtension(String filename) {
        String extension = getFileExtension(filename);
        if ("pdf".equalsIgnoreCase(extension)) {
            return "application/pdf";
        } else if ("doc".equalsIgnoreCase(extension) || "docx".equalsIgnoreCase(extension)) {
            return "application/msword";
        }
        // 为其他文件类型添加更多条件
        else {
            return "application/octet-stream"; // 默认值
        }
    }

    // 从文件名中提取文件扩展名
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex > 0) ? filename.substring(dotIndex + 1) : "";
    }
//    @GetMapping("/{flag}")   //  1697438073596-avatar.png
//    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
//        OutputStream os;
//        try {
//            if (StrUtil.isNotEmpty(flag)) {
//                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
//                response.setContentType("application/octet-stream");
//                byte[] bytes = FileUtil.readBytes(filePath + flag);
//                os = response.getOutputStream();
//                os.write(bytes);
//                os.flush();
//                os.close();
//            }
//        } catch (Exception e) {
//            System.out.println("文件下载失败");
//        }
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String flag;
        synchronized (FileController.class) {
            flag = System.currentTimeMillis() + "";
            ThreadUtil.sleep(1L);
        }
        String fileName = file.getOriginalFilename();
        try {
            if (!FileUtil.isDirectory(filePath)) {
                FileUtil.mkdir(filePath);
            }
            // 文件存储形式：时间戳-文件名
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);  // ***/manager/files/1697438073596-avatar.png
            System.out.println(fileName + "--上传成功");

        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        String http = "http://" + ip + ":" + port + "/files/";
        return Result.success(http + flag + "-" + fileName);  //  http://localhost:9090/files/1697438073596-avatar.png
    }


    private String saveFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 文件保存路径
            String saveDir = "src/main/resources/books"; // 替换为实际的保存路径//
            File directory = new File(saveDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 构造文件的完整路径
            File targetFile = new File(directory, fileName);

            // 保存文件
            file.transferTo(new File(saveDir + File.separator + fileName));
            return "books/" + fileName; // 返回相对路径

        }
        return null;
        }
    }


