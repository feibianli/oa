package com.example.departmentservice.permission;

import cn.dev33.satoken.stp.StpInterface;
import com.example.departmentservice.mapper.DepartmentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPermission implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("user-add");
        list.add("user-delete");
        list.add("user-update");
        list.add("user-get");
        list.add("article-get");
        System.out.println("用户权限列表" + list);
        return list;
    }

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //通过这个id去数据库查询权限
        Integer id = Integer.parseInt((String) loginId);
        String role = departmentMapper.selectRole(id);
        List<String> list = new ArrayList<String>();
        list.add(role);
        list.add("user");
        //list.add("super-admin");
        System.out.println("用户角色列表" + list);
        return list;
    }


}
