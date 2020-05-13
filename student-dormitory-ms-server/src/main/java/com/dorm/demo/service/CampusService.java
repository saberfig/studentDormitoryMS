package com.dorm.demo.service;

import com.dorm.demo.dao.CampusDao;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusService {
    @Autowired
    CampusDao campusDao;

    public List<CampusInfo> getCampusInfo(){
        return campusDao.getCampusInfo();
    }
}
