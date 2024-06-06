package com.example.userservice.service;




import com.example.userservice.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectDepartment(String name);
    List<User> selectJob (String name);
    String selectRole(int id);



}
