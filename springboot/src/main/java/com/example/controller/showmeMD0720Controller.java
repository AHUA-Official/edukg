package com.example.controller;//
//import com.example.common.config.JwtInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;使用开源的第三方库给指定的md文本转成HTML页面     之后直接返回回去
//请求是get    询问的是md文本的名字    md文本直接遍历搜索D盘下
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Resource
//    private JwtInterceptor jwtInterceptor;
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 映射本地文件夹到URL路径
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("file:D:/edukg/springboot/src/main/resources/images/");
//    }
//    // 加
//package com.example.controller;
////定义一个Get请求   全部在Controller里面实现
//


//然后 你说   这个接口咋实现  给我一个参考  和详细的接口文档呗
import org.commonmark.node.Document;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class showmeMD0720Controller {

    private static final String MARKDOWN_PATH = "D:/markdowns/";

    @GetMapping(value = "/markdown/{filename}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getMarkdownAsHtml(@PathVariable String filename) throws IOException {
        String cleanedFilename = filename.replaceAll("[<>:\"/\\\\|?*]", "");
        Path path = Paths.get(MARKDOWN_PATH, "a" + ".md");
        Resource resource = new FileSystemResource(path);

        if (!resource.exists()) {
            throw new IllegalArgumentException("File not found: " + filename);
        }

        String markdownContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Document document = (Document) Parser.builder().build().parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}