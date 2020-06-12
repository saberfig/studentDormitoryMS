package com.dorm.demo.dao;

import com.dorm.demo.pojo.Room;
import com.dorm.demo.pojo.responsePOJO.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomDAO extends JpaRepository<Room, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from Room where id=:id and dormId=:dormId and dormCampusId=:campusId")
    void deleteBy3Id(@Param("id") String id,@Param("dormId") String dormId,@Param("campusId") String campusId);
}