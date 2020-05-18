package com.dorm.demo.service;

import com.dorm.demo.dao.MaStudentDAO;
import com.dorm.demo.pojo.MaStudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaStudentInfoService {
    @Autowired
    MaStudentDAO maStudentDAO;
    public List<MaStudentInfo> findInfo(String dmid){
        return maStudentDAO.getMaStudentInfo(dmid);
    }

}
