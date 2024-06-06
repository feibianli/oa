package com.example.workservice.controller;


import com.example.workservice.pojo.Department;
import com.example.workservice.pojo.Work;
import com.example.workservice.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/work")
//@SaCheckLogin
public class WorkController {

    @Autowired
    private WorkService workService;  //两个
    //新建工作小组
    @RequestMapping("/newGroup")//调用department服务
    public void updateGroup(String member,int group_id){
        workService.updateGroup(member,group_id);
    }
    //修改工作内容
    @RequestMapping("/updatejob")//调用department服务
    public void updatejob(String jobDec, int id){
        workService.updatejob(jobDec,id);
    }

    //修改工作进度
    @RequestMapping("/updateProgress")
    public void updateProgress(String progress ,int id){
        workService.updateProgress(progress,id);
    }
    //查询是否完成
    @RequestMapping("/isFinish")
    public List<Work> isFinish(int id){
        return workService.isFinish(id);
    }
}
