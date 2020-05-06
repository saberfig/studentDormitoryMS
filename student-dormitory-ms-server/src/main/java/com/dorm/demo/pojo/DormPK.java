package com.dorm.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DormPK implements Serializable {
    private String id;
    private String campusId;

    @Column(name = "id", nullable = false, length = 10)
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "campus_id", nullable = false, length = 10)
    @Id
    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DormPK that = (DormPK) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(campusId, that.campusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, campusId);
    }
}
