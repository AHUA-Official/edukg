package com.example.mapper;

import com.example.entity.AAFurinaminiodocuments;
import com.example.entity.AAFurinaminioprasejson;

import java.util.List;

public interface AAFurinaminioMapper {

    int insertdoc(AAFurinaminiodocuments miniodocuments);
    int insertjson(AAFurinaminioprasejson prasejson);
    String checkdocid( String documentId);
    List<AAFurinaminiodocuments> selectDocumentsNeedParse(int limit);
    int updateDocumentStatus( String documentId, String status);

}
