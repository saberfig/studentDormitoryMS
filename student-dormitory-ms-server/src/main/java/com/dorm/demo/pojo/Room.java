package com.dorm.demo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room", schema = "programdb", catalog = "")
@IdClass(RoomPK.class)
public class Room {
    private String id;
    private String dormId;
    private String dormCampusId;

    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "dorm_id", nullable = false, length = 10)
    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    @Id
    @Column(name = "dorm_campus_id", nullable = false, length = 10)
    public String getDormCampusId() {
        return dormCampusId;
    }

    public void setDormCampusId(String dormCampusId) {
        this.dormCampusId = dormCampusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room that = (Room) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dormId, that.dormId) &&
                Objects.equals(dormCampusId, that.dormCampusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dormId, dormCampusId);
    }
}
