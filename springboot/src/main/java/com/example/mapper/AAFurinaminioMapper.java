package com.example.mapper;

import com.example.entity.AAFurinaminiodocuments;
import com.example.entity.AAFurinaminioprasejson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
@Mapper
public interface AAFurinaminioMapper {

    int insertdoc(AAFurinaminiodocuments miniodocuments);
    int insertjson(AAFurinaminioprasejson prasejson);
    String checkdocid( String documentId);
    List<AAFurinaminiodocuments> selectDocumentsNeedParse(int limit);
    @Options(useGeneratedKeys = false)
    int updateDocumentStatus( String documentId, String status);
    List<AAFurinaminiodocuments> selectDocumentsALL();

    List<AAFurinaminiodocuments> updatewaitstatus ( String documentId,String status);

}
