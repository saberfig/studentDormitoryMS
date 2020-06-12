package com.dorm.demo.service;

import com.dorm.demo.dao.RoomDAO;
import com.dorm.demo.dao.RoomInfoDao;
import com.dorm.demo.pojo.Campus;
import com.dorm.demo.pojo.Room;
import com.dorm.demo.pojo.responsePOJO.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomInfoDao roomInfoDao;

    @Autowired
    RoomDAO roomDao;

    public List<RoomInfo> getRoomInfo(){

        return roomInfoDao.getRoomInfo();
    }

    public void addRoom(Room room){
        roomDao.save(room);
    }

    public void deleteBy3Id(Room room){
        roomDao.deleteBy3Id(room.getId(),room.getDormId(),room.getDormCampusId());
    }
}
