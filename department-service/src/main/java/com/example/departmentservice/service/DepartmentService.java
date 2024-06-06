package com.example.departmentservice.service;



import com.example.departmentservice.pojo.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> selectMember();
    List<Department> selectNotice();
    List<Department> selectJob();
    void updateNotice();


    void joinDepartment(String name ,String department);//userController 调用
    void deletemember(String name ,String department);//userController 调用



    void updateGroup(String member,int group_id);//workController 调用
    void updatejob(String jobDec,int id);//workController 调用
}
