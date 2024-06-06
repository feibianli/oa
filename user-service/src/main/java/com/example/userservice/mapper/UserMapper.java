package com.example.userservice.mapper;


import com.example.userservice.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectDepartment(String name);
    List<User> selectJob (String name);
    String selectRole(int id);

}
