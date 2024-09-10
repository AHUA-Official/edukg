package com.example.service;

import cn.hutool.core.lang.UUID;
import com.example.entity.PDFUploadRecord;
import com.example.mapper.PDFUploadRecordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PDF服务类，提供PDF上传和管理功能
 */
@Service
public class PDFService {
    @Value("${pdf.upload.path}")
    private String uploadPath; // 存储PDF文件的路径
    @Resource
    private PDFUploadRecordMapper PDFUploadRecordMapper; // PDF上传记录的Mapper，用于数据库操作
    @Value("{pdf.upload.path}")
    private  String  PicturePath;


//功能    做PDF的切分    把PDF的一页切成3张图片   然后做成一个网页  之类 的    吧    模仿的就是小说阅读器那种    每张图片的id可以绑定对应的评论   这个依靠一个csv文件来存储评论啥的
    //具体给controller调的函数
    // 做PDF的切分     给定在uploadPath/PDFUploadRecord表中登记的实际文件名字  然后给出对应的产生发图片的url的json列表
@Value("{pdf.upload.path}") // 假设使用Spring Boot，注入应用上下文路径
private String contextPath;

    // 新增方法：处理PDF并返回图片URL列表
    public List<Map<String, String>> handlePdfAndGenerateUrls(String pdfActualFileName) throws IOException {
        // 假设从数据库或配置中获取PDF的实际路径
        String pdfFilePath = uploadPath + File.separator + pdfActualFileName;
        File pdfFile = new File(pdfFilePath);

        // 图片输出目录保持不变
        String outputDir = PicturePath;

        // 切分PDF并保存图片
        List<File> imageFiles = splitPdfPageToImages(pdfFile, outputDir);

        // 构造图片URL列表
        List<Map<String, String>> imageUrlList = new ArrayList<>();
        for (File imageFile : imageFiles) {
            String imageName = imageFile.getName();
            // 构建图片访问URL，这里假设存在一个/images/的映射
            URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(contextPath)
                    .path("/images/")
                    .path(imageName)
                    .build()
                    .toUri();

            imageUrlList.add(Map.of("imageUrl", uri.toString()));
        }
        System.out.println(imageUrlList.toString());
        return imageUrlList;
    }


    /**
     * 将PDF页面分割为图片。
     *
     * 此方法将指定的PDF文件的每一页都分割成多张图片，然后将这些图片保存到指定的输出目录中。
     * 默认将每页分割为3部分（上、中、下）。
     *
     * @param pdfFile 指定的PDF文件。
     * @param outputDir 保存分割后图片的输出目录。
     * @return 包含所有分割后图片文件路径的列表。
     * @throws IOException 如果处理PDF文件或保存图片时发生IO错误。
     */
    public List<File> splitPdfPageToImages(File pdfFile, String outputDir) throws IOException {
        PDDocument document = PDDocument.load(pdfFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        //D:\\edukg\\PDFS
        outputDir = "D:\\\\edukg\\\\PDFS";

        // 仅用于调试目的，确保路径正确设置
        // 实际部署时应直接使用传入的 outputDir
        // outputDir = "D:\\\\edukg\\\\PDFS";

        // 使用传入的outputDir，但增加检查及创建目录的逻辑
        File dir = new File(outputDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs(); // 创建目录，包括所有必需但不存在的父目录
            if (!created) {
                throw new IOException("Failed to create the output directory: " + outputDir);
            }
        }
        int pageCount = document.getNumberOfPages();
        List<File> imageFiles = new ArrayList<>();

        for (int i = 0; i < pageCount; i++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
            // 分割当前页面为多个部分
            List<BufferedImage> splitImages = splitImage(image, 3);
            for (int j = 0; j < splitImages.size(); j++) {
                File outputFile = new File(outputDir, String.format("page_%d_part_%d.png", i, j));
                ImageIO.write(splitImages.get(j), "PNG", outputFile);
                imageFiles.add(outputFile);
            }
        }

        document.close();
        return imageFiles;
    }

    /**
     * 分割图片。
     *
     * 此方法将指定的原始图片分割成多个部分。
     * 当前实现将图片简单地分割为指定数量的行或列。
     *
     * @param original 原始图片。
     * @param parts 要分割的行数或列数。
     * @return 包含分割后的图片的列表。
     */
    private List<BufferedImage> splitImage(BufferedImage original, int parts) {
        List<BufferedImage> splitImages = new ArrayList<>();
        int imageWidth = original.getWidth();
        int imageHeight = original.getHeight();
        int partHeight = imageHeight / parts; // 计算每部分的高度

        for (int i = 0; i < parts; i++) {
            int startY = i * partHeight;
            int endY = (i + 1) * partHeight;

            // 处理最后一部分，以确保包含所有剩余的高度
            if (i == parts - 1) {
                endY = imageHeight;
            }

            BufferedImage splitImage = new BufferedImage(imageWidth, endY - startY, BufferedImage.TYPE_INT_RGB);
            // 复制原始图片的对应区域到分割后的图片
            for (int x = 0; x < imageWidth; x++) {
                for (int y = startY; y < endY; y++) {
                    splitImage.setRGB(x, y - startY, original.getRGB(x, y));
                }
            }
            splitImages.add(splitImage);
        }
        return splitImages;
    }
    /**
     * 上传PDF文件并将其记录到数据库中
     * @param file 上传的PDF文件
     * @return 返回文件的访问URL
     */
    public String uploadAndRegisterPDF(MultipartFile file) {
        try {
            // 生成唯一的文件名
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
            // 将文件存储到指定路径
            Path filePath = Paths.get(uploadPath, uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            // 创建PDF上传记录并设置相关属性
            PDFUploadRecord record = new PDFUploadRecord();
            record.setStoredName(uniqueFileName);
            record.setOriginalName(file.getOriginalFilename());
            record.setUploadTime(new Date());
            String currentUser = "system"; // 需要实现获取当前登录用户的方法
            record.setUploadUser(currentUser);

            // 将文件信息注册到数据库
            add(record);

            // 返回文件的访问URL
            return "/static/" + uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload and register PDF", e);
        }
    }

    /**
     * 生成唯一的文件名
     * @param originalFilename 原始文件名
     * @return 返回生成的唯一文件名
     */
    private String generateUniqueFileName(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }

    /**
     * 将文件信息写入CSV文件
     * @param storedName 存储后的文件名
     * @param originalName 原始文件名
     * @param uploadTime 上传时间
     */
    private void registerToCSV(String storedName, String originalName, Date uploadTime) {
        try (FileWriter writer = new FileWriter(new File(uploadPath, "uploads.csv"), true)) {
            writer.append(storedName)
                    .append(',')
                    .append(originalName)
                    .append(',')
                    .append(uploadTime.toString())
                    .append('\n');
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to CSV", e);
        }
    }

    /**
     * 新增PDF上传记录到数据库
     * @param PDFUploadRecord PDF上传记录实体
     */
    public void add(PDFUploadRecord PDFUploadRecord) {
        PDFUploadRecordMapper.insert(PDFUploadRecord);
    }

    /**
     * 根据ID删除PDF上传记录
     * @param id PDF上传记录的ID
     */
    public void deleteById(Integer id) {
        PDFUploadRecordMapper.deleteById(id);
    }

    /**
     * 批量删除PDF上传记录
     * @param ids 要删除的PDF上传记录ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            PDFUploadRecordMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改PDF上传记录
     * @param PDFUploadRecord PDF上传记录实体
     */
    public void updateById(PDFUploadRecord PDFUploadRecord) {
        PDFUploadRecordMapper.updateById(PDFUploadRecord);
    }

    /**
     * 根据ID查询PDF上传记录
     * @param id PDF上传记录的ID
     * @return 返回对应的PDF上传记录
     */
    public PDFUploadRecord selectById(Integer id) {
        return PDFUploadRecordMapper.selectById(id);
    }

    /**
     * 查询所有PDF上传记录
     * @param PDFUploadRecord PDF上传记录实体，可用于过滤查询条件
     * @return 返回PDF上传记录列表
     */
    public List<PDFUploadRecord> selectAll(PDFUploadRecord PDFUploadRecord) {
        return PDFUploadRecordMapper.selectAll(PDFUploadRecord);
    }

    /**
     * 分页查询PDF上传记录
     * @param PDFUploadRecord PDF上传记录实体，可用于过滤查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return 返回分页信息
     */
    public PageInfo<PDFUploadRecord> selectPage(PDFUploadRecord PDFUploadRecord, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PDFUploadRecord> list = PDFUploadRecordMapper.selectAll(PDFUploadRecord);
        return PageInfo.of(list);
    }

}
