package com.dorm.demo.pojo.responsePOJO;

import com.dorm.demo.pojo.Campus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CampusInfo {
    @Id
    private String id;
    private String name;
    private int dormNum;
    private int roomNum;
    private int bedNum;

    public CampusInfo(){}

    public CampusInfo(String id,String name,int dormNum,int roomNum,int bedNum){
        this.id=id;
        this.name=name;
        this.dormNum=dormNum;
        this.roomNum=roomNum;
        this.bedNum=bedNum;
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

    public int getDormNum() {
        return dormNum;
    }

    public void setDormNum(int dormNum) {
        this.dormNum = dormNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }
}
