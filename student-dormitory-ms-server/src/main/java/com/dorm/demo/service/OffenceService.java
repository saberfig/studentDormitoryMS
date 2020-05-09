package com.dorm.demo.service;

import com.dorm.demo.dao.OffenceDao;
import com.dorm.demo.pojo.Offence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffenceService {
    @Autowired
    OffenceDao offenceDao;

    public void addOrUpdate(Offence offence){
        offenceDao.save(offence);
    }
}
