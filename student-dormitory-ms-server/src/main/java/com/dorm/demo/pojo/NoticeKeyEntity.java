package com.dorm.demo.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "notice_key", schema = "programdb", catalog = "")
public class NoticeKeyEntity {
    private int id;
    private Timestamp time;
    private String description;
    private String superManagerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "super_manager_id", nullable = false, length = 10)
    public String getSuperManagerId() {
        return superManagerId;
    }

    public void setSuperManagerId(String superManagerId) {
        this.superManagerId = superManagerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeKeyEntity that = (NoticeKeyEntity) o;
        return id == that.id &&
                Objects.equals(time, that.time) &&
                Objects.equals(description, that.description) &&
                Objects.equals(superManagerId, that.superManagerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, description, superManagerId);
    }
}
