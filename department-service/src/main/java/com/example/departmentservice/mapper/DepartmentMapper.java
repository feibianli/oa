package com.example.departmentservice.mapper;


import com.example.departmentservice.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> selectMember();
    List<Department> selectNotice();
    List<Department> selectJob();
    //发布部门公告
    void updateNotice();
    String selectRole(int id);
    //
    void joinDepartment(String name ,String department);    //userController 调用
    void deletemember(String name ,String department);      //userController 调用


    void updateGroup(String member,int group_id);                   //workController 调用
    void updatejob(String jobDec,int id);                    //workController 调用
}
