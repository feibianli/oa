package com.example.departmentservice.service.imp;


import com.example.departmentservice.mapper.DepartmentMapper;
import com.example.departmentservice.pojo.Department;
import com.example.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> selectMember(){
        return departmentMapper.selectMember();
    }
    public List<Department> selectNotice(){
        return departmentMapper.selectNotice();
    }
    public List<Department> selectJob(){
        return departmentMapper.selectJob();
    }
    public void updateNotice(){
        departmentMapper.updateNotice();
    }
    public void joinDepartment(String name ,String department){
        departmentMapper.joinDepartment(name,department);
    }
    public void deletemember(String name ,String department){
        departmentMapper.deletemember(name ,department);
    }


    public void updateGroup(String member,int group_id){
        departmentMapper.updateGroup(member, group_id);
    }
    public void updatejob(String jobDec,int id){
        departmentMapper.updatejob(jobDec,id);
    }
}
