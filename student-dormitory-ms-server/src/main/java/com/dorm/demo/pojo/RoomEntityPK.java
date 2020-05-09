package com.dorm.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoomEntityPK implements Serializable {
    private String id;
    private String dormId;
    private String dormCampusId;

    @Column(name = "id", nullable = false, length = 10)
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "dorm_id", nullable = false, length = 10)
    @Id
    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    @Column(name = "dorm_campus_id", nullable = false, length = 10)
    @Id
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
        RoomEntityPK that = (RoomEntityPK) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dormId, that.dormId) &&
                Objects.equals(dormCampusId, that.dormCampusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dormId, dormCampusId);
    }
}
