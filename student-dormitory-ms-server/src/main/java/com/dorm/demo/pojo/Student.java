package com.dorm.demo.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

//pojo里的用于链接数据库，创建和数据库里一样的类型
// 后端如何接收前端的 JS 对象呢？我们很自然地想到在需要创建一个形式上一致的 Java 类。
//教程里的user类，在第三节修改 User 类代码如下，以建立对数据库的映射
//@Entity 表示这是一个实体类
//@Table(name=“student”) 表示对应的表名是 student
@Entity
@Table(name = "student", schema = "programdb", catalog = "")
public class Student {
    private String id;
    private String name;
    private String sex;
    private String major;
    private String tel;
    private Integer yearIn;
    private Date birth;
    private String psw;
    //@Id: 标注用于声明一个实体类的属性映射为数据库的主键列。该属性通常置于属性声明语句之前，可与声明语句同行，也可写在单独行上。 @Id标注也可置于属性的getter方法之前
    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //@Basic表示一个简单的属性到数据库表的字段的映射,对于没有任何标注的 getXxxx() 方法,默认即为
    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "major", nullable = true, length = 40)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
    @Column(name = "year_in", nullable = true)
    public Integer getYearIn() {
        return yearIn;
    }

    public void setYearIn(Integer yearIn) {
        this.yearIn = yearIn;
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
        Student that = (Student) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(major, that.major) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(yearIn, that.yearIn) &&
                Objects.equals(birth, that.birth) &&
                Objects.equals(psw, that.psw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, major, tel, yearIn, birth, psw);
    }
}
