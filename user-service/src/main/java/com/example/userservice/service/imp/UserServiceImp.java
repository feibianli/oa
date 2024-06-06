package com.example.userservice.service.imp;



import com.example.userservice.mapper.UserMapper;
import com.example.userservice.pojo.Department;
import com.example.userservice.pojo.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserMapper userMapper;
    public List<User> selectDepartment(String name){
        return userMapper.selectDepartment(name);
    }
    public List<User> selectJob (String name){
        return userMapper.selectJob(name);
    }
    public String selectRole(int id){
        return userMapper.selectRole(id);
    }


}
