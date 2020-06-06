package com.dorm.demo.pojo.responsePOJO;

import com.dorm.demo.pojo.Room;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomInfo {
    @Id

    private String dormName;
    private String campusName;
    private String dormId;
    private Integer bedNum;
    private String roomId;
    private String campusId;
    private String name;

    public RoomInfo(){}

    public RoomInfo(String dormName,String campusName,String dormId,String campusId,String roomId,Integer bedNum,String name){
        this.dormId=dormId;
        this.dormName=dormName;
        this.campusName=campusName;
        this.campusId = campusId;
        this.roomId = roomId;
        this.bedNum= bedNum;
    }

    public String getDormId() { return dormId; }

    public void setDormId(String dormId) { this.dormId = dormId;  }

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

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
