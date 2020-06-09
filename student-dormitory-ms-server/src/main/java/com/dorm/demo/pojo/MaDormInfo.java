package com.dorm.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MaDormInfo {
    @Id

    private String dormName;
    private String campusName;
    private String maDormName;

    public MaDormInfo(){}

    public MaDormInfo(String dormName,String campusName,String maDormName){
        this.dormName=dormName;
        this.campusName=campusName;
        this.maDormName=maDormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getDormName() {
        return dormName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusName() {
        return campusName;
    }


    public void setMaDormName(String maDormName) {
        this.maDormName = maDormName;
    }

    public String getMaDormName() {
        return maDormName;
    }
}
