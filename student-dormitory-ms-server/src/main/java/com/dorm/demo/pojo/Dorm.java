package com.dorm.demo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dorm", schema = "programdb", catalog = "")
@IdClass(DormPK.class)
public class Dorm {
    private String id;
    private String campusId;
    private String name;

    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "campus_id", nullable = false, length = 10)
    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dorm that = (Dorm) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(campusId, that.campusId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, campusId, name);
    }
}
