package com.dorm.demo.service;
import com.dorm.demo.dao.DormManagerDAO;
import com.dorm.demo.pojo.DormManager;
import com.dorm.demo.pojo.SuperManager;
import com.dorm.demo.dao.SuperManagerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperManagerService {
    @Autowired
    SuperManagerDAO superManagerDAO;//创建一个Data Access Object（数据访问对象，DAO）

    //通过id获取student表条目
    public SuperManager getById(String id){
        return superManagerDAO.findById(id);
    }
    //通过id和密码获取student表条目
    public SuperManager get(String id, String psw){
        System.out.println(id);
        System.out.println(psw);
        return superManagerDAO.findByIdAndPsw(id,psw);
    }
}
