├── ReadMe.md           // 帮助文档
    
    ├── department-service   // 部门服务
      ├── src.main
        ├──java.com.example.departmentservice
          ├──config     //satoken配置文件
          ├──controller  //查询成员，公告，工作内容，审核加入部门，审核请假，创建小组（workcontroller调用），更新工作内容（workcontroller调用）
          ├──mapper
          ├──permission //satoken配置用户权限
          ├──pojo
          ├──service
        ├──resources    //核心配置文件
    ├── user-service        // 用户服务
       ├── src.main
          ├──java.com.example.userservice
            ├──chat        //用户聊天功能
            ├──config     //satoken配置文件
            ├──controller  //一些接口，用户登陆，登出，是否登录，加密密码，审核请假，查询本部门公告，申请加入，请假，转部门
            ├──exception  //异常返回给前端
            ├──mapper      
            ├──permission //satoken配置用户权限
            ├──pojo
            ├──service    
          ├──resources    //核心配置文件
    ├── work-service             // 工作服务
        ├── src.main
          ├──java.com.example.workservice
            ├──controller  //更新工作进度，查询是否完成，创建工作小组，更新工作内容
            ├──mapper    
            ├──pojo
            ├──service //创建工作小组和更新工作内容通过resttemplate调用department接口实现
            ├──resources  //核心配置文件
    
  

