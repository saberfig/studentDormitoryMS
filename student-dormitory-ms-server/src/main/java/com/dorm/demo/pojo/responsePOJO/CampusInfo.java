package com.dorm.demo.pojo.responsePOJO;

import com.dorm.demo.pojo.Campus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CampusInfo {
    @Id
    private String id;
    private String name;
    private Integer dormNum;
    private Integer roomNum;
    private Integer bedNum;

    public CampusInfo(){}

    public CampusInfo(String id,String name,Integer dormNum,Integer roomNum,Integer bedNum){
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
        if(this.dormNum==null){
            this.dormNum=0;
        }
        return dormNum;
    }

    public void setDormNum(int dormNum) {
        this.dormNum = dormNum;
    }

    public int getRoomNum() {
        if(this.roomNum==null){
            this.roomNum=0;
        }
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getBedNum() {
        if(this.bedNum==null){
            this.bedNum=0;
        }
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }
}
