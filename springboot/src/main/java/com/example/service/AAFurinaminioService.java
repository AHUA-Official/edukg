package com.example.service;

import com.example.entity.AAFurinaminiodocuments;
import com.example.mapper.AAFurinaminioMapper;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AAFurinaminioService {
    @Resource
    private MinioClient minioClient;
    @Resource
    private AAFurinaminioMapper miniodocumentsMapper;
    public  String getCamelPinYin(String hz, boolean type) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String m;
        StringBuilder r = new StringBuilder();
        try {
            for (char value : hz.toCharArray()) {
                // 判断是否为汉字字符
                if (Character.toString(value).matches("[\\u4E00-\\u9FA5]+")) {
                    // 取出该汉字全拼的第一种读音并连接到字符串m后
                    m = PinyinHelper.toHanyuPinyinStringArray(value, format)[0];
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串m后
                    m = Character.toString(value);
                }
                if (type) {
                    r.append(m.substring(0, 1).toUpperCase()).append(m.substring(1));
                } else {
                    r.append(m);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return r.toString();
    }


    public void testMinIOClient() {
        System.out.println(minioClient);
    }

    public Map<String, Object> uploadFile(File file, String myobject, String symbol, String version, String chain) {
        // 实现上传文件的逻辑 写入表格和写入minio的OSS存储
        //存储桶   写死为 furina
        //存储路径   存储桶下面  然后第一层写该文件名转英语/拼音的文件   第二层写入该文件的文件名
        //难点   任何一个文件  怎么转拼音
        //minio的操作  我在下面注释掉的代码里面有
        String filename = file.getName();
        String object = myobject;
        String extractedSymbol = (symbol != null && !symbol.isEmpty()) ? symbol : "furina";
        String extractedVersion = (version != null && !version.isEmpty()) ? version : "1";
        String extractedChain = (chain != null && !chain.isEmpty()) ? chain : "1";
        // String miniourl = "aaa";//@@@
        String needParse = "True";
        String Previw = "can not preview";
        Map<String, Object> response = new HashMap<>();



        if ("document".equals(object)) {
            String bucketName = "furina";
            String namepath = filename.substring(0, filename.lastIndexOf("."));
            String pinyinNamepath = getCamelPinYin(namepath, true);
            String englishFilename = getCamelPinYin(filename, false);
            try {
                // 上传文件到 MinIO
                String objectName = pinyinNamepath + "/" + englishFilename;
                ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(new FileInputStream(file), file.length(), -1)
                        .build()
        );
                System.out.println( objectWriteResponse);


                //File file = new File("E:\\性能测试\\面试避坑.md");
////        1  对象的流传输参数
//        ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
//                .bucket("furina")
//                .object("aaa/tse.md")
//                .stream(new FileInputStream(file), file.length(), -1)
//                .build()
//        );
//        System.out.println(objectWriteResponse);
                String miniourl = "http://49.234.47.133:9000/" + bucketName + "/" + objectName; // 假设 MinIO 的访问 URL


                AAFurinaminiodocuments mydoc = new AAFurinaminiodocuments(
                        filename,
                        miniourl, // 这里可以替换为实际的 MinIO URL
                        extractedSymbol,
                        needParse,
                        Previw
                );
                miniodocumentsMapper.insertdoc(mydoc);
                response.put("message", "File uploaded successfully");
                response.put("url", miniourl);
                System.out.println(response);

                return response;



            }  catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                    NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
                response.put("error", "Failed to upload file");
                response.put("message", e.getMessage());
                System.out.println(response);
                return response;
            }


        }

        if ("parse_json".equals(object)) {
            System.out.println("我喜欢你");
        }


        return null;
    }}
//
//@SpringBootTest
//public class AAFurinaMinioTest {
//    @Resource
//    private AAFurinaminioService minioService;
//    @Resource
//    private MinioClient  minioClient;
//
//
//
//    @Test
//    void  zltry1(){
//        minioService.testMinIOClient();
//
//    }
//
//
//
//    @Test
////    判断bucket是否存在
//    void test01() throws Exception {
////        minioClient.bucketExists()
//        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("download").build());
//        System.out.println("myfile目录是否存在：" + isBucketExists);
//    }
//
//    @Test
////
//    void test02() throws Exception {
//        String bucketName = "furinabuf";
//        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
//        if (!isBucketExists) {
//
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
//        } else {
//            System.out.println("bucket已经存在，不需要创建.");
//        }
//
//        String policyJsonString = "{\"Version\" : \"2012-10-17\",\"Statement\":[{\"Sid\":\"PublicRead\",\"Effect\":\"Allow\",\"Principal\":{\"AWS\":\"*\"},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}]}";
//        //创建存储桶的时候，设置该存储桶里面的文件的访问策略，运行公开的读；
////        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket("furina").config(policyJsonString).build());
//        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
//                .bucket(bucketName)
//                .config(policyJsonString)//json串，里面是访问策略
//                .build());
//    }
//
//    @Test
////    打印所有的桶
//    void test03() throws Exception {
//        List<Bucket> bucketList = minioClient.listBuckets();
//        bucketList.forEach(bucket -> {
//            System.out.println(bucket.name() + " -- " + bucket.creationDate());
//        });
//    }
//
//    @Test
////    删除桶
//    void test04() throws Exception {
//        minioClient.removeBucket(RemoveBucketArgs.builder().bucket("furinabuf").build());
//    }
//
//    //---------------------------------------------------------------------------
//
//    @Test
//        //文件操作
//
//    void test05() throws Exception {
//        File file = new File("E:\\性能测试\\面试避坑.md");
////        1  对象的流传输参数
//        ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
//                .bucket("furina")
//                .object("aaa/tse.md")
//                .stream(new FileInputStream(file), file.length(), -1)
//                .build()
//        );
//        System.out.println(objectWriteResponse);
//
//        UploadObjectArgs myfile = UploadObjectArgs.builder()
//                .bucket("furina")
//                .object("test2.md")
//                .filename("E:\\性能测试\\面试避坑.md")
//                .build();
//        ObjectWriteResponse objectWriteResponse2 = minioClient.uploadObject(myfile
//        );
//        System.out.println(objectWriteResponse);
//
//    }
//
//
//
//
//
//
//
//
//
//    @Test
//    void test06() throws Exception {
//        StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder()
//                .bucket("furina")
//                .object("tse.md")
//                .build()
//        );
//        System.out.println(statObjectResponse);
//    }
//
//    @Test
//    void test07() throws Exception {
//        String presignedObjectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
//                .bucket("furina")
//                .object("tse.md")
//                .expiry(300, TimeUnit.MINUTES)
//                .method(Method.GET)
//                .build());
//        System.out.println(presignedObjectUrl);
//    }
//
//    @Test
//    void test08() throws Exception {
//        GetObjectResponse getObjectResponse = minioClient.getObject(GetObjectArgs.builder()
//                .bucket("furina")
//                .object("tse.md")
//                .build());
//
//        System.out.println(getObjectResponse.transferTo(new FileOutputStream("D:\\pig.md")));
//    }
//
//    @Test
//    void test09() throws Exception {
//        Iterable<Result<Item>> listObjects = minioClient.listObjects(ListObjectsArgs.builder()
//                .bucket("furina")
//                .build());
//
//        listObjects.forEach( itemResult -> {
//            try {
//                Item item = itemResult.get();
//                System.out.println(item.objectName());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    @Test
//    void test10() throws Exception {
//        minioClient.removeObject(RemoveObjectArgs.builder()
//                .bucket("furina")
//                .object("tse.md")
//                .build());
//    }
//
//}



//求   完善uploadfile里面的集成minio的部分
