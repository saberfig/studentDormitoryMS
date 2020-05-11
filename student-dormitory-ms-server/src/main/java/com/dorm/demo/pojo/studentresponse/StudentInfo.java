package com.dorm.demo.pojo.studentresponse;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentInfo {
    @Id
    String id;//其实没用
    String campusname;
    String dormnum;
    String roomnum;
    String bednum;

    public StudentInfo(){

    }

    public StudentInfo(String campusname, String dormnum, String roomnum, String bednum){
        this.campusname = campusname;
        this.dormnum = dormnum;
        this.roomnum = roomnum;
        this.bednum = bednum;
    }


    public String getCampusname() {
        return campusname;
    }

    public void setCampusname(String campusname) {
        this.campusname = campusname;
    }

    public String getDormnum() {
        return dormnum;
    }

    public void setDormnum(String dormnum) {
        this.dormnum = dormnum;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public String getBednum() {
        return bednum;
    }

    public void setBednum(String bednum) {
        this.bednum = bednum;
    }
}

