package com.dorm.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student_Bed {
    @Id
    private String id;//学生学号
    private String name;
    //private String sex;
    //private String major;
    //private String tel;
    //private Integer yearIn;
    //private Date birth;
    //private String psw;

    private String bed_id;
    private String roomId;
    private String roomDormId;
    private String roomDormCampusId;
    //private String studentId;

    public Student_Bed(String id,
                       String name,
                       String bed_id,
                       String roomId,
                       String roomDormId,
                       String roomDormCampusId){
        this.id=id;
        this.name=name;
        this.bed_id=bed_id;
        this.roomId=roomId;
        this.roomDormId=roomDormId;
        this.roomDormCampusId=roomDormCampusId;
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

    public String getBed_id() {
        return bed_id;
    }

    public void setBed_id(String bed_id) {
        this.bed_id = bed_id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomDormId() {
        return roomDormId;
    }

    public void setRoomDormId(String roomDormId) {
        this.roomDormId = roomDormId;
    }

    public String getRoomDormCampusId() {
        return roomDormCampusId;
    }

    public void setRoomDormCampusId(String roomDormCampusId) {
        this.roomDormCampusId = roomDormCampusId;
    }
}
