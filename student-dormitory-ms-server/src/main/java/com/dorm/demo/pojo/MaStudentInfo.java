package com.dorm.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MaStudentInfo {
    @Id
    private String id;//学号
    private String name;//姓名
    private String roomId;//宿舍号
    private String sex;//性别
    private String major;//专业
    private String tel;//电话

    public MaStudentInfo() {}

    public MaStudentInfo(String id, String name, String roomId, String sex, String major, String tel){
        this.id=id;
        this.name=name;
        this.roomId=roomId;
        this.sex=sex;
        this.major=major;
        this.tel=tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
