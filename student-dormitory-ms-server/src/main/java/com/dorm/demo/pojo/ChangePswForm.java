package com.dorm.demo.pojo;

public class ChangePswForm {

    String id;
    String oldpsw;
    String newpsw;

    public ChangePswForm(String id, String oldpsw, String newpsw){
        this.id = id;
        this.oldpsw = oldpsw;
        this.newpsw = newpsw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldpsw() {
        return oldpsw;
    }

    public void setOldpsw(String oldpsw) {
        this.oldpsw = oldpsw;
    }

    public String getNewpsw() {
        return newpsw;
    }

    public void setNewpsw(String newpsw) {
        this.newpsw = newpsw;
    }
}
