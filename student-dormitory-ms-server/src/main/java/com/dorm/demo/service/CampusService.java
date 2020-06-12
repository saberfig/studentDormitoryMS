package com.dorm.demo.service;

import com.dorm.demo.dao.CampusDao;
import com.dorm.demo.dao.CampusInfoDao;
import com.dorm.demo.pojo.Campus;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusService {
    @Autowired
    CampusInfoDao campusInfoDao;
    @Autowired
    CampusDao campusDao;

    public String addCampus(Campus campus) {
        campusDao.save(campus);
        return "success";
    }

    public void deleteById(String campusId){
        campusDao.deleteByCampusId(campusId);
    }

    public void modifyNameById(String id,String name){
        campusDao.modifyNameById(id,name);
    }

    public List<CampusInfo> getCampusInfo(){
        List<CampusInfo> ls=campusInfoDao.getCampusInfo();
        return ls;
    }
}
