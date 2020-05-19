package com.dorm.demo.service;

import com.dorm.demo.dao.RoomDao;
import com.dorm.demo.pojo.responsePOJO.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomDao roomDao;

    public List<RoomInfo> getRoomInfo(){

        return roomDao.getRoomInfo();
    }
}
