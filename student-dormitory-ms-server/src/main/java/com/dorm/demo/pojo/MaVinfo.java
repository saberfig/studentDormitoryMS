package com.dorm.demo.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class MaVinfo {
    @Id
    private int id;
    private Date timeIn;
   // private Timestamp timeOut;
    private String vname;
    private String videntity;
    private String reason;
    private String studentId;

    public MaVinfo(){}
    public MaVinfo(int id, Date timeIn, String vname, String videntity, String reason, String studentId){
        this.id=id;
      this.timeIn=timeIn;
       this.vname=vname;
        this.videntity=videntity;
        this.reason=reason;
        this.studentId=studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//
    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }
//
//    public Timestamp getTimeOut() {
//        return timeOut;
//    }
//
//    public void setTimeOut(Timestamp timeOut) {
//        this.timeOut = timeOut;
//    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVidentity() {
        return videntity;
    }

    public void setVidentity(String videntity) {
        this.videntity = videntity;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
