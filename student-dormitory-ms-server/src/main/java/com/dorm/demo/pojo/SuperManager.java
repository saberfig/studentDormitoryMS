package com.dorm.demo.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "super_manager", schema = "programdb", catalog = "")
public class SuperManager {
    private String id;
    private Date birth;
    private String sex;
    private String name;
    private String tel;
    private String psw;

    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "birth", nullable = true)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
    @Column(name = "tel", nullable = true, length = 11)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "psw", nullable = false, length = 32)
    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperManager that = (SuperManager) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(birth, that.birth) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(name, that.name) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(psw, that.psw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birth, sex, name, tel, psw);
    }
}
