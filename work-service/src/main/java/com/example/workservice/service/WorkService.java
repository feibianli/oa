package com.example.workservice.service;



import com.example.workservice.pojo.Department;
import com.example.workservice.pojo.Work;

import java.util.List;

public interface WorkService {

    void updateGroup(String member,int group_id);//调用department服务
    void updatejob(String jobDec, int id);//调用department服务
    void updateProgress(String progress,int id);
    List<Work> isFinish(int id);
}
