package com.example.service;

import com.example.entity.AAFurinaminiodocuments;
import com.example.entity.AAFurinaminioprasejson;
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
import org.springframework.transaction.annotation.Transactional;
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

    public Map<String, Object> uploadFile(File file, String myobject, String symbol, String version, String chain,String documentuuid) {
        // 实现上传文件的逻辑 写入表格和写入minio的OSS存储
        //存储桶   写死为 furina
        //存储路径   存储桶下面  然后第一层写该文件名转英语/拼音的文件   第二层写入该文件的文件名
        //难点   任何一个文件  怎么转拼音
        //minio的操作  我在下面注释掉的代码里面有
        String filename = file.getName();
        String object = myobject;
        String extractedSymbol = (symbol != null && !symbol.isEmpty()) ? symbol : "furina";
        int extractedVersion = (version != null && !version.isEmpty()) ? Integer.parseInt(version) : 1;
        int  extractedChain = (chain != null && !chain.isEmpty()) ? Integer.parseInt(chain) : 1;
        String extradocumentuuid = (documentuuid != null && !chain.isEmpty()) ? documentuuid : "furina";
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

                String miniourl = "http://49.234.47.133:9000/" + bucketName + "/" + objectName; // 假设 MinIO 的访问 URL


                AAFurinaminiodocuments mydoc = new AAFurinaminiodocuments(
                        null,
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

            String bucketName = "furina";
            String namepath = filename.substring(0, filename.lastIndexOf("."));
            String pinyinNamepath = getCamelPinYin(namepath, true);
            String englishFilename = getCamelPinYin(filename, false);
            try {
                // 上传文件到 MinIO
                String objectName = pinyinNamepath + "/" + englishFilename;
                String  json_bucketspath = "jsonsets"+"/" + englishFilename;
                ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(new FileInputStream(file), file.length(), -1)
                        .build()
                );
                System.out.println( objectWriteResponse);

                ObjectWriteResponse jsonobjectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(json_bucketspath)
                        .stream(new FileInputStream(file), file.length(), -1)
                        .build()
                );
                System.out.println( objectWriteResponse);


                String miniourl = "http://49.234.47.133:9000/" + bucketName + "/" + objectName; // 假设 MinIO 的访问 URL


                AAFurinaminioprasejson   myprase_json = new AAFurinaminioprasejson(
                        extradocumentuuid,
                        filename,
                        miniourl,
                        extractedChain,
                        extractedVersion,
                        extractedSymbol
                );

                String existingDocumentId = miniodocumentsMapper.checkdocid(extradocumentuuid);
                if (existingDocumentId != null && !existingDocumentId.isEmpty()) {
                    miniodocumentsMapper.insertjson(myprase_json);

                response.put("message", "File uploaded successfully");
                response.put("url", miniourl);
                System.out.println(response);

                return response;}else{
                    response.put("message", "对不起 你提供的文档解析的源文件不存在");
                    response.put("url", miniourl);
                    System.out.println(response);

                }




            }  catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                    NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
                response.put("error", "Failed to upload file");
                response.put("message", e.getMessage());
                System.out.println(response);
                return response;
            }

        }


        return null;
    }

    public Map<String, Object> checkDocumentId(String documentId) {
    Map<String, Object> response = new HashMap<>();
    String existingDocumentId = miniodocumentsMapper.checkdocid(documentId);
    if (existingDocumentId != null && !existingDocumentId.isEmpty()) {
        response.put("message", "Document ID exists.");
        response.put("documentId", existingDocumentId);
        response.put("data",existingDocumentId);
    } else {
        response.put("message", "Document ID does not exist.");
    }
    return response;
}
    public List<AAFurinaminiodocuments> selectallminio(){
        List<AAFurinaminiodocuments> needParseDocuments = miniodocumentsMapper.selectDocumentsALL();
        return  needParseDocuments;
    }

    //给出接口  返回一条miniodocuments needprase="True的记录"并把该记录记录成Waiting
    @Transactional
    public List<AAFurinaminiodocuments> updateNeedParseToWaiting(int limit) {
        // 获取 needParse="True" 的记录，限制返回的数量
        List<AAFurinaminiodocuments> needParseDocuments = miniodocumentsMapper.selectDocumentsNeedParse(limit);


        // 遍历记录并更新状态为 Waiting
        for (AAFurinaminiodocuments document : needParseDocuments) {

            System.out.println(document.toString());
            System.out.println("你好 我喜欢你");
            System.out.println(document.toString());
            // 更新数据库记录

            String id = document.getId();
            System.out.println(id);

            Integer a = miniodocumentsMapper.updateDocumentStatus(id, "Waiting");
            System.out.println(a);
            // 更新对象状态
            document.setNeedParse("Waiting");

        }


        return needParseDocuments;
    }


}


