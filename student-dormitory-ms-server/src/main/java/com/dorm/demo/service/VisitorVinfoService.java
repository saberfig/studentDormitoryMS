package com.dorm.demo.service;

import com.dorm.demo.dao.VisitorVinfoDAO;
import com.dorm.demo.pojo.VisitorVinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorVinfoService {
    @Autowired
    VisitorVinfoDAO visitorVinfoDAO;

    public void add(VisitorVinfo visitorVinfo){
        visitorVinfoDAO.save(visitorVinfo);
    }

}
