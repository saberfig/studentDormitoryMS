package com.dorm.demo.pojo;

public class LoginForm {
    String id;
    String psw;
    String status;

    LoginForm(String id, String psw, String status){
        this.id = id;
        this.psw = psw;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
