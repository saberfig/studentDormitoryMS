package com.dorm.demo.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "visitor_vinfo", schema = "programdb", catalog = "")
public class VisitorVinfo {
    private int id;
    private Timestamp timeIn;
    private Timestamp timeOut;
    private String vname;
    private String videntity;
    private String reason;
    private String studentId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_in", nullable = true)
    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    @Basic
    @Column(name = "time_out", nullable = true)
    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    @Basic
    @Column(name = "vname", nullable = true, length = 20)
    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    @Basic
    @Column(name = "videntity", nullable = true, length = 20)
    public String getVidentity() {
        return videntity;
    }

    public void setVidentity(String videntity) {
        this.videntity = videntity;
    }

    @Basic
    @Column(name = "reason", nullable = true, length = -1)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "student_id", nullable = false, length = 10)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitorVinfo that = (VisitorVinfo) o;
        return id == that.id &&
                Objects.equals(timeIn, that.timeIn) &&
                Objects.equals(timeOut, that.timeOut) &&
                Objects.equals(vname, that.vname) &&
                Objects.equals(videntity, that.videntity) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeIn, timeOut, vname, videntity, reason, studentId);
    }
}
