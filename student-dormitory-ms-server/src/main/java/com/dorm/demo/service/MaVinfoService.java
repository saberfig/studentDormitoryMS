package com.dorm.demo.service;

import com.dorm.demo.dao.MaStudentDAO;
import com.dorm.demo.dao.MaVinfoDAO;
import com.dorm.demo.pojo.MaVinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaVinfoService {
    @Autowired
    MaVinfoDAO maVinfoDAO;
        public List<MaVinfo> findInfo(String dmid){
            return maVinfoDAO.getVisitorVinfo(dmid);
        }
}
