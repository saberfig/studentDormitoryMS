package com.dorm.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OffenInfo {
    @Id
    private int id;//违纪表中的id
    private String name;//姓名
    private String studentId;//学号
    private String description;//描述

    public OffenInfo() {}

    public OffenInfo(int id, String name, String studentId, String description){
        this.id=id;
        this.name=name;
        this.studentId=studentId;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
