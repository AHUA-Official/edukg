package com.example.mapper;


import com.example.entity.AAFurinaCSREF;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AAFurinaCSREFMapper {


    List<AAFurinaCSREF> selectAll(AAFurinaCSREF csref);

    void insert(AAFurinaCSREF courseStudentref);

    List<AAFurinaCSREF> selectbycourseid(Integer courseId);
}