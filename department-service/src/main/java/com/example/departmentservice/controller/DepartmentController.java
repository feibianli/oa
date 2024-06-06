package com.example.departmentservice.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.departmentservice.pojo.Department;
import com.example.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
//@SaCheckLogin
public class DepartmentController {
    private static final  String USERNAME = "hello";
    private static final  String PASSWORD = "123456";


//    @RequestMapping("doLogin")
//    public SaResult doLogin(String username , String password , int id ){
//        //在浏览器强制弹出认证框
//        //SaBasicUtil.check("sa:123456");
//        if (username.equals(USERNAME)&&password.equals(PASSWORD)){
//            StpUtil.login (id);
//            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//            System.out.println(tokenInfo);
//            return SaResult.ok("登录成功，回话ID为"+StpUtil.getLoginId()+". Token为:" +StpUtil.getTokenValue());
//        }
//        return SaResult.error("登录失败");
//    }

    @Autowired
    private DepartmentService departmentService;
        //@SaCheckLogin
        @RequestMapping("/selectMember")
        public List<Department> selectMember (){
            return departmentService.selectMember();
        }
        //@SaCheckLogin
        @RequestMapping("/selectNotice")
        public List<Department> selectNotice (){
            return departmentService.selectNotice();
        }
        //@SaCheckLogin
        @RequestMapping("/selectJob")
        public List<Department> selectJob (){
            return departmentService.selectJob();
        }

        @RequestMapping("/updateNotice")
        //@SaCheckPermission(value ="user-add",orRole = "admin")
        public void updateNotice(){
            departmentService.updateNotice();
        }



    //管理员审核加入部门
    @SaCheckRole("admin")
    @RequestMapping("/admin/auditjoinDepartment")
    public void joinDepartment(boolean b,String name ,String department){

        departmentService.joinDepartment(name,department);
        if(b){
            System.out.println("审核通过,恭喜"+name+"加入"+ department);

        }
        else{
            System.out.println("审核未通过，不能加入部门");
        }

    }
    //管理员审核请假
    @SaCheckRole("admin")
    @RequestMapping("/admin/auditTransferLeave")
    public void deletemember(boolean b,String name ,String department){

            if(b){
                System.out.println("你已经离开"+department +"可以加入其他部门");
                departmentService.deletemember(name,department);
            }else{
                System.out.println("审核未通过，不能离开本部门");
            }
    }
    @RequestMapping("/updateGroup")//workController 调用
    @SaIgnore
    public void updateGroup(String member,int group_id){
        departmentService.updateGroup(member,group_id);
    }
    @RequestMapping("/updatejob")//workController 调用
    @SaIgnore
    public void updatejob(String jobDec,int id){
        departmentService.updatejob(jobDec,id);
    }

}
