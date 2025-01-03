package com.example;


import com.example.entity.AAFurinaminiodocuments;
import com.example.mapper.AAFurinaminioMapper;
import com.example.service.AAFurinaminioService;
import io.minio.*;

import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class AAFurinaMinioTest {
    @Resource
    private AAFurinaminioService minioService;
    @Resource
    private MinioClient  minioClient;
    @Autowired
    private AAFurinaminioMapper aAFurinaminioMapper;


    @Test
    void  zltry1(){
        minioService.testMinIOClient();

    }
    @Test
    void zlsql1(){
        File file = new File("E:\\性能测试\\面试避坑.md");
        // 调用 uploadFile 方法，这里需要根据您的方法签名传递正确的参数
        Map<String, Object> result = minioService.uploadFile(file, "document", "symbol", "1", "1","furina");
        System.out.println(result);
    }

    @Test
    void zlsql2() {
        File file = new File("E:\\性能测试\\面试避坑.md");
        // 调用 uploadFile 方法，这里需要根据您的方法签名传递正确的参数
        Map<String, Object> result = minioService.uploadFile(file, "parse_json", "symbol", "1", "1", "db5a83f1-506c-4062-8f7d-b7304e62f219");
        System.out.println(result);
    }

    @Test
    void zlsql3() {

        Map<String, Object> result = minioService.checkDocumentId("db5a83f1-506c-4062-8f7d-b7304e62f219");
        System.out.println(result);
    }
    @Test
    void zlsql4() {

        List <AAFurinaminiodocuments> result = minioService.updateNeedParseToWaiting(10);
        System.out.println(result);
    }

    @Test
    void zlsql22() {
        // 调用 updateNeedParseToWaiting 方法，传入限制数量
        List<AAFurinaminiodocuments> result = minioService.updateNeedParseToWaiting(10);

        // 验证结果是否为空
        assertNotNull(result, "结果列表不应为空");

        // 验证结果列表的大小是否符合预期
        assertTrue(result.size() <= 10, "结果列表的大小应小于等于10");

        // 验证每个文档的状态是否已更新为 "Waiting"
        for (AAFurinaminiodocuments document : result) {
            assertEquals("Waiting", document.getNeedParse(), "文档状态应更新为 'Waiting'");
        }
    }
    @Test
    void zlsql221() {
        // 调用 updateNeedParseToWaiting 方法，传入限制数量
        List<AAFurinaminiodocuments> result =aAFurinaminioMapper.selectDocumentsALL();
        System.out.println(result);

        // 验证结果是否为空
        assertNotNull(result, "结果列表不应为空");

        // 验证每个文档的状态是否已更新为 "Waiting"
        for (AAFurinaminiodocuments document : result) {
            System.out.println(document);

        }
    }
    @Test
    void testupdate() {

        String documentId = "0545cfc6-c364-45ca-b932-a4b692c0963d"; // 替换成实际的文档ID
        String status = "waiting"; // 新的状态

        // 调用更新方法
        int updatedRows = aAFurinaminioMapper.updateDocumentStatus(documentId, status);

    }





    @Test
    void py(){
        String py =minioService.getCamelPinYin("我喜欢你.pdf.txt",false);
        System.out.println(py);

    }




    @Test
//    判断bucket是否存在
    void test01() throws Exception {
//        minioClient.bucketExists()
        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("download").build());
        System.out.println("myfile目录是否存在：" + isBucketExists);
    }

    @Test
//
    void test02() throws Exception {
        String bucketName = "furinabuf";
        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExists) {

            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            System.out.println("bucket已经存在，不需要创建.");
        }

        String policyJsonString = "{\"Version\" : \"2012-10-17\",\"Statement\":[{\"Sid\":\"PublicRead\",\"Effect\":\"Allow\",\"Principal\":{\"AWS\":\"*\"},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}]}";
        //创建存储桶的时候，设置该存储桶里面的文件的访问策略，运行公开的读；
//        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket("furina").config(policyJsonString).build());
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                .bucket(bucketName)
                .config(policyJsonString)//json串，里面是访问策略
                .build());
    }

    @Test
//    打印所有的桶
    void test03() throws Exception {
        List<Bucket> bucketList = minioClient.listBuckets();
        bucketList.forEach(bucket -> {
            System.out.println(bucket.name() + " -- " + bucket.creationDate());
        });
    }

    @Test
//    删除桶
    void test04() throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket("furinabuf").build());
    }

    //---------------------------------------------------------------------------

    @Test
    //文件操作

    void test05() throws Exception {
        File file = new File("E:\\性能测试\\面试避坑.md");
//        1  对象的流传输参数
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket("furina")
                .object("aaa/tse.md")
                .stream(new FileInputStream(file), file.length(), -1)
                .build()
        );
        System.out.println(objectWriteResponse);

        UploadObjectArgs myfile = UploadObjectArgs.builder()
                .bucket("furina")
                .object("test2.md")
                .filename("E:\\性能测试\\面试避坑.md")
                .build();
        ObjectWriteResponse objectWriteResponse2 = minioClient.uploadObject(myfile
        );
        System.out.println(objectWriteResponse);

    }









    @Test
    void test06() throws Exception {
        StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder()
                .bucket("furina")
                .object("tse.md")
                .build()
        );
        System.out.println(statObjectResponse);
    }

    @Test
    void test07() throws Exception {
        String presignedObjectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket("furina")
                .object("tse.md")
                .expiry(300, TimeUnit.MINUTES)
                .method(Method.GET)
                .build());
        System.out.println(presignedObjectUrl);
    }

    @Test
    void test08() throws Exception {
        GetObjectResponse getObjectResponse = minioClient.getObject(GetObjectArgs.builder()
                .bucket("furina")
                .object("tse.md")
                .build());

        System.out.println(getObjectResponse.transferTo(new FileOutputStream("D:\\pig.md")));
    }

    @Test
    void test09() throws Exception {
        Iterable<Result<Item>> listObjects = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket("furina")
                .build());

        listObjects.forEach( itemResult -> {
            try {
                Item item = itemResult.get();
                System.out.println(item.objectName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void test10() throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket("furina")
                .object("tse.md")
                .build());
    }

}
