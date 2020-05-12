package com.dorm.demo.pojo.studentresponse;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class StudentNotice {
    @Id
    String id;
    Timestamp date;
    String content;
    String stringdate;

    public StudentNotice(){

    }

    public StudentNotice(String content, Timestamp date){
        this.content=content;
        this.date=date;
    }

    public void Trans(){
        this.stringdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date.getTime());
    }

    public String getStringdate() {
        return stringdate;
    }

    public void setStringdate(String stringdate) {
        this.stringdate = stringdate;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
/*
    @Id
    String id;//其实没用
    Timestamp date;
    Date testdate;
    String content;
    String datestring;

    public StudentNotice(){

    }

    public StudentNotice(Date date, String content){
        this.date = new Timestamp(date.getTime());
        this.content = content;
        this.datestring = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date);
        System.out.println("进入一个新的构造器");
        System.out.println(this.date);
        System.out.println(this.content);
        System.out.println(this.datestring);
    }


    public StudentNotice(String content){
        //this.date = date;
        this.content = content;
        //this.datestring = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date);
    }

    public StudentNotice(Timestamp date, String content){
        this.date = date;
        this.content = content;
        this.datestring = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date);
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatestring() {
        return datestring;
    }

    public void setDatestring(String datestring) {
        this.datestring = datestring;
    }

     */
}
