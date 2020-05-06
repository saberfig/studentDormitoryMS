package com.dorm.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BedPK implements Serializable {
    private String id;
    private String roomId;
    private String roomDormId;
    private String roomDormCampusId;

    @Column(name = "id", nullable = false, length = 10)
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "room_id", nullable = false, length = 10)
    @Id
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Column(name = "room_dorm_id", nullable = false, length = 10)
    @Id
    public String getRoomDormId() {
        return roomDormId;
    }

    public void setRoomDormId(String roomDormId) {
        this.roomDormId = roomDormId;
    }

    @Column(name = "room_dorm_campus_id", nullable = false, length = 10)
    @Id
    public String getRoomDormCampusId() {
        return roomDormCampusId;
    }

    public void setRoomDormCampusId(String roomDormCampusId) {
        this.roomDormCampusId = roomDormCampusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedPK that = (BedPK) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(roomDormId, that.roomDormId) &&
                Objects.equals(roomDormCampusId, that.roomDormCampusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, roomDormId, roomDormCampusId);
    }
}
