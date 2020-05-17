package com.dorm.demo.service;

import com.dorm.demo.dao.DormDao;
import com.dorm.demo.pojo.responsePOJO.DormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormService {
    @Autowired
    DormDao dormDao;

    public List<DormInfo> getDormInfo(){
        return dormDao.getDormInfo();
    }
}
