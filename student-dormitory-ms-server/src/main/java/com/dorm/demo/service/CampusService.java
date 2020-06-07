package com.dorm.demo.service;

import com.dorm.demo.controller.manager.CampusInfoController;
import com.dorm.demo.dao.CampusDao;
import com.dorm.demo.pojo.Campus;
import com.dorm.demo.pojo.NoticeNorm;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CampusService {
    @Autowired
    CampusDao campusDao;

    public String addCampus(@RequestParam("name") String name, @RequestParam("id") String id) {
        campusDao.addCampus(name,id);
        return "success";
    }

    public List<CampusInfo> getCampusInfo(){
        return campusDao.getCampusInfo();
    }
}
