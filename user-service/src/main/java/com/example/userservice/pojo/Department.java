package com.example.userservice.pojo;

public class Department {
    private String member;
    private String notice;
    private String jobDec;
    private int group_id;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

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
}
