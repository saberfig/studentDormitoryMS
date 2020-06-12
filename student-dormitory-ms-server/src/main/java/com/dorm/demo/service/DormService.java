package com.dorm.demo.service;

import com.dorm.demo.dao.DormDAO;
import com.dorm.demo.dao.DormInfoDao;
import com.dorm.demo.pojo.Dorm;
import com.dorm.demo.pojo.responsePOJO.DormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormService {
    @Autowired
    DormInfoDao dormInfoDao;

    @Autowired
    DormDAO dormDao;

    public List<DormInfo> getDormInfo(){

        return dormInfoDao.getDormInfo();
    }

    public void addDorm(Dorm dorm){
        dormDao.save(dorm);
    }

    public void deleteDorm(Dorm dorm){
        System.out.println(dorm.getId());
        System.out.println(dorm.getCampusId());
        dormDao.deleteByIdAndCampusId(dorm.getId(),dorm.getCampusId());
    }

    public void modifyDorm(Dorm dorm){
        dormDao.modifyNameByIdAndCampusId(dorm.getId(),dorm.getCampusId(),dorm.getName());
    }
}
