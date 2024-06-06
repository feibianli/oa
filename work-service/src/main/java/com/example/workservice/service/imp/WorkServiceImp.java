package com.example.workservice.service.imp;

import com.example.workservice.mapper.WorkMapper;
import com.example.workservice.pojo.Department;
import com.example.workservice.pojo.Work;
import com.example.workservice.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkServiceImp implements WorkService {
    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private RestTemplate restTemplate;
    //调用department服务
    public void updateGroup(String member,int group_id){

        ResponseEntity<List<Department>> responseEntity =
                restTemplate.exchange("http://localhost:8082/department/updateGroup?member={member}&group_id={group_id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Department>>() {
                        },
                        member,
                        group_id);
        List<Department> DepartmentList = responseEntity.getBody();
    }
    //调用department服务
    public void updatejob(String jobDec, int id){
        ResponseEntity<List<Department>> responseEntity =
                restTemplate.exchange("http://localhost:8082/department/updatejob?jobDec={jobDec}&id={id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Department>>() {
                        },
                        jobDec,
                        id);
        List<Department> DepartmentList = responseEntity.getBody();
    }

    public void updateProgress(String progress,int id){
        workMapper.updateProgress(progress,id);
    }
    public List<Work> isFinish(int id){
        return workMapper.isFinish(id);
    }
}
