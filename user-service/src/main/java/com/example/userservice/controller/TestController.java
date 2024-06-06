package com.example.userservice.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.util.SaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@SaCheckLogin
public class TestController {

    //游客可以访问
    @SaIgnore
    @RequestMapping("/getList")
    public SaResult getList(){
        return SaResult.ok("无需登录接口");
    }

//    @SaCheckLogin
//    @RequestMapping("/select")
//    public List<Department> select (){
//        return departmentService.selectMember();
//    }

    //只有超级管理员才能删除
    @SaCheckRole("super-admin")
    @RequestMapping("/delete")
    public SaResult delete (){
        return SaResult.ok("删除成功");
    }

    @SaCheckPermission(value ={"user-add","user-all"}, mode = SaMode.OR)
    @RequestMapping("/add")
    public SaResult add(){
        return SaResult.ok("添加成功");
    }

    @RequestMapping("/update")
    @SaCheckPermission(value ="user-add",orRole = "admin")
    public SaResult update(){
        return SaResult.ok("更新成功");
    }

}
