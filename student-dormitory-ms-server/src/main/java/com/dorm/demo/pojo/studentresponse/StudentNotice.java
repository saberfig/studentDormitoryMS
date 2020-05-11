package com.dorm.demo.pojo.studentresponse;

import java.sql.Timestamp;

public class StudentNotice {

    Timestamp date;
    String content;

    StudentNotice(Timestamp date, String content){
        this.date = date;
        this.content = content;
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
}
