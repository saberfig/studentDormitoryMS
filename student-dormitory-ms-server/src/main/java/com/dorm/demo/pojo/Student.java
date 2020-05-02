package com.dorm.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="student")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Student {
    @Id
    @Column(name="id")
    String id;
    @Column(name="name")
    String name;
    @Column(name="sex")
    String sex;
    @Column(name="major")
    String major;
    @Column(name="tel")
    String tel;
    @Column(name="year_in")
    int year_in;
    @Column(name="birth")
    Date birth;
    @Column(name="psw")
    String psw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getYear_in() {
        return year_in;
    }

    public void setYear_in(int year_in) {
        this.year_in = year_in;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}