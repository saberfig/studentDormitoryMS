package com.dorm.demo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bed", schema = "programdb", catalog = "")
@IdClass(BedPK.class)
public class Bed {
    private String id;
    private String roomId;
    private String roomDormId;
    private String roomDormCampusId;
    private String studentId;

    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "room_id", nullable = false, length = 10)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Id
    @Column(name = "room_dorm_id", nullable = false, length = 10)
    public String getRoomDormId() {
        return roomDormId;
    }

    public void setRoomDormId(String roomDormId) {
        this.roomDormId = roomDormId;
    }

    @Id
    @Column(name = "room_dorm_campus_id", nullable = false, length = 10)
    public String getRoomDormCampusId() {
        return roomDormCampusId;
    }

    public void setRoomDormCampusId(String roomDormCampusId) {
        this.roomDormCampusId = roomDormCampusId;
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
        Bed bedEntity = (Bed) o;
        return Objects.equals(id, bedEntity.id) &&
                Objects.equals(roomId, bedEntity.roomId) &&
                Objects.equals(roomDormId, bedEntity.roomDormId) &&
                Objects.equals(roomDormCampusId, bedEntity.roomDormCampusId) &&
                Objects.equals(studentId, bedEntity.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, roomDormId, roomDormCampusId, studentId);
    }
}
