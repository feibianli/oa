package com.example.userservice.pojo;

public class User {
    private String member;
    private int id;
    private String notice;
    private String jobDec;
    //加入部门


    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getJobDec() {
        return jobDec;
    }

    public void setJobDec(String jobDec) {
        this.jobDec = jobDec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public void join(String name){
        System.out.println("我是"+name+"我要加入这个部门");

    }

}
