package com.dorm.demo.service;

import com.dorm.demo.pojo.DormManager;
import com.dorm.demo.dao.DormManagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DormManagerService {

    @Autowired
    DormManagerDAO dormManagerDAO;

    //通过id获取student表条目
    public DormManager getById(String id){
        return dormManagerDAO.findById(id);
    }


    //通过id和密码获取student表条目
    public DormManager get(String id, String psw){
        System.out.println("传入DormManagerService");
        System.out.println("传入service搜索的id："+id);
        System.out.println("传入service搜索的psw:"+psw);
        return dormManagerDAO.findByIdAndPsw(id,psw);
    }
}
