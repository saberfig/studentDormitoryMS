package com.dorm.demo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goods", schema = "programdb", catalog = "")
public class GoodsEntity {
    private int id;
    private String name;
    private Integer status;
    private String dormId;
    private String dormCampusId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "dorm_id", nullable = false, length = 10)
    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    @Basic
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
        GoodsEntity that = (GoodsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status) &&
                Objects.equals(dormId, that.dormId) &&
                Objects.equals(dormCampusId, that.dormCampusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, dormId, dormCampusId);
    }
}
