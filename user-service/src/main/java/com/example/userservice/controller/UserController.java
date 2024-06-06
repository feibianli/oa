package com.example.userservice.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

import com.example.userservice.pojo.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final  String USERNAME = "jwj";
    private static final  String PASSWORD = "123456";


    @RequestMapping("doLogin")
    public SaResult doLogin(String username ,String password ,int id ){
        //在浏览器强制弹出认证框
        //SaBasicUtil.check("sa:123456");
        if (username.equals(USERNAME)&&password.equals(PASSWORD)){
            StpUtil.login (id);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            System.out.println(tokenInfo);
            return SaResult.ok("登录成功，回话ID为"+StpUtil.getLoginId()+". Token为:" +StpUtil.getTokenValue());
        }
        return SaResult.error("登录失败");
    }


    //退出登录方法
    @RequestMapping("/signOut")
    public SaResult signOut (){
        String loginId = null;
        if (StpUtil.isLogin()){
            loginId= (String)StpUtil.getLoginId();
            StpUtil.logout();
        }
        return SaResult.ok("回话ID为 " +loginId + "的用户注销成功" );

    }

    //查询是否登录
    @RequestMapping("/isLogin")
    public SaResult isLogin (){
        if (StpUtil.isLogin()){
            return SaResult.ok("回话ID是否登录:" + StpUtil.isLogin()+",回话ID为" + StpUtil.getLoginId());

        }
        return SaResult.ok("回话是否登录"+ StpUtil.isLogin());
    }
    @SaIgnore
    @RequestMapping("/encodePassword")
    public void encodePassword ()throws Exception{
        String md5= SaSecureUtil.md5("123456");
        String md5BySalt = SaSecureUtil.md5BySalt("123456","salt");
        System.out.println("MD5加密"+md5);
        System.out.println("MD5加盐加密"+md5BySalt);
    }




    @RequestMapping("/admin/auditLeave")
    public void auditleave(boolean b){
        if(b){
            System.out.println("审核通过，你可以开始你的假期了");
        }
        else{
            System.out.println("审核未通过，不能请假，已驳回，请重新说明原因");
        }
    }
    @RequestMapping("/selectDepartment")
    public List<User> selectDepartment (String name){
        return userService.selectDepartment(name);
    }
    @RequestMapping("/selectJob")
    public List<User> selectJob (String name){

        return userService.selectJob(name);
    }
    //加入部门申请
    @RequestMapping("join")
    public void joinDepartment(String name ,String department){
        System.out.println(name +"申请加入"+department );

        //user.join(name);
    }
    //转部门申请
    @RequestMapping("/transfer")
    public void transferDepartment(String originDepartment ,String name, String newDepartment){
        System.out.println(name + "申请从:"+originDepartment+" 转到 :"+ newDepartment);
    }
    //请假申请
    @RequestMapping("/leave")
    public void leaveDepartment(String name ,String reason){
        System.out.println(name +"由于" +reason +"需要请假");
    }

}
