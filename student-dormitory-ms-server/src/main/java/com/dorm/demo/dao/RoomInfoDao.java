package com.dorm.demo.dao;

import com.dorm.demo.pojo.responsePOJO.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomInfoDao extends JpaRepository<RoomInfo, Integer> {
    //使用原生MySQL查询时，返回Object对象，或者Object[]
    //@Query(nativeQuery = true, value = "" +
    //        "select c.dorm_name,campus.name as campus_name,c.dorm_id,c.campus_id,c.room_id,c.bed_num,campus.id as name from (select b.bed_num,b.dorm_id,b.campus_id,b.room_id,dorm.name as dorm_name from (select bed_num,a.room_dorm_id as dorm_id,a.room_dorm_campus_id as campus_id,a.room_id from (select count(*) as bed_num,room_dorm_id,room_dorm_campus_id,room_id from bed group by bed.room_dorm_id,room_dorm_campus_id,room_id) as a left join room on a.room_id = room.id and a.room_dorm_id = room.dorm_id and a.room_dorm_campus_id = room.dorm_campus_id)as b left join dorm on b.dorm_id = dorm.id and b.campus_id = dorm.campus_id)as c left join campus on c.campus_id = campus.id ")
    //List<RoomInfo> getRoomInfo();
    @Query(nativeQuery = true, value = "" +
            "select b.name as dorm_name,campus.name as campus_name,b.dormId as dorm_id,b.campusId as campus_id,b.roomId as room_id,b.bed_num,campus.id as name from (select a.roomId,a.dormId,a.campusId,a.bed_num,dorm.name from(select room.id as roomId,room.dorm_id as dormId,room.dorm_campus_id as campusId,count(student_id)as bed_num from room left join bed on bed.room_id=room.id and bed.room_dorm_id = room.dorm_id and bed.room_dorm_campus_id = room.dorm_campus_id group by room.id,room.dorm_id,room.dorm_campus_id)as a left join dorm on a.dormId=dorm.id and a.campusId = dorm.campus_id) as b left join campus on b.campusId = campus.id")
    List<RoomInfo> getRoomInfo();
}