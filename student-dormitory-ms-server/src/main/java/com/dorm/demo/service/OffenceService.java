package com.dorm.demo.service;

import com.dorm.demo.dao.OffenceDao;
import com.dorm.demo.pojo.Offence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffenceService {
    @Autowired
    OffenceDao offenceDao;

    public void add(Offence offence){

        offenceDao.save(offence);
    }
}
