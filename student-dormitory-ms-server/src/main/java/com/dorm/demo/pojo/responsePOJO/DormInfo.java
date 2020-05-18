package com.dorm.demo.pojo.responsePOJO;

import com.dorm.demo.pojo.Dorm;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DormInfo {
    @Id

    private String dormName;
    private String campusName;
    private String dormId;
    private int bedNum;
    private int roomNum;

    public DormInfo(){}

    public DormInfo(String dormName,String campusName,String dormId,int bedNum,int roomNum){
        this.dormId=dormId;
        this.dormName=dormName;
        this.campusName=campusName;
        this.roomNum=roomNum;
        this.bedNum=bedNum;
    }

    public String getId() {
        return dormId;
    }

    public void setId(String dormId) {
        this.dormId = dormId;
    }

    public String getdormName() {
        return dormName;
    }

    public void setdormName(String dormName) {
        this.dormName = dormName;
    }

    public String getcampusName() {
        return campusName;
    }

    public void setcampusName(String campusName) {
        this.campusName = campusName;
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
