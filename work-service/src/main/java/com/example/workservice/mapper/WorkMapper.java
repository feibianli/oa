package com.example.workservice.mapper;


import com.example.workservice.pojo.Work;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkMapper {

    void updateProgress(String progress,int id);
    List<Work> isFinish(int id);
    String selectRole(int id);
}
