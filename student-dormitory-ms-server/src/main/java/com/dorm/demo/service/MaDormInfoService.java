package com.dorm.demo.service;

import com.dorm.demo.dao.MaDormInfoDao;
import com.dorm.demo.pojo.MaDormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaDormInfoService {
    @Autowired
    MaDormInfoDao maDormInfoDao;
    public List<MaDormInfo> findInfo(String dmid){
        return maDormInfoDao.getMaDormInfo(dmid);
    }
}
