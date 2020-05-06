package com.dorm.demo.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "student_borrow_goods", schema = "programdb", catalog = "")
public class StudentBorrowGoods {
    private int id;
    private Timestamp timeStart;
    private Timestamp timeStop;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_start", nullable = true)
    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    @Basic
    @Column(name = "time_stop", nullable = true)
    public Timestamp getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(Timestamp timeStop) {
        this.timeStop = timeStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBorrowGoods that = (StudentBorrowGoods) o;
        return id == that.id &&
                Objects.equals(timeStart, that.timeStart) &&
                Objects.equals(timeStop, that.timeStop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStart, timeStop);
    }
}
