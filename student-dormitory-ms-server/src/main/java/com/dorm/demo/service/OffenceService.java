package com.dorm.demo.service;

import com.dorm.demo.pojo.Offence;
import com.dorm.demo.dao.OffenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffenceService {
    @Autowired
    OffenceDao offenceDao;

    public void addOrUpdate(Offence offence){
        offenceDao.save(offence);
    }
}
