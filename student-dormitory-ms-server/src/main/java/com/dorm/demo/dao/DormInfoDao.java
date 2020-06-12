package com.dorm.demo.dao;

import com.dorm.demo.pojo.responsePOJO.DormInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormInfoDao extends JpaRepository<DormInfo, Integer> {
    //使用原生MySQL查询时，返回Object对象，或者Object[]
    @Query(nativeQuery = true, value = "" +
            "select d.dorm_name,d.campus_name,d.dorm_id,c.bed_num,c.room_num,c.campus_id as name from (select campus_id,dorm.name as dorm_name,dorm.id as dorm_id,campus.name as campus_name from dorm left join campus on dorm.campus_id = campus.id) as d left join (select dorm_id,dorm_campus_id as campus_id,bed_num,room_num from (select count(*)as room_num,dorm_id,dorm_campus_id from room group by room.dorm_campus_id,room.dorm_id) as b left join (select count(*) as bed_num,room_dorm_id,room_dorm_campus_id from bed group by bed.room_dorm_id,room_dorm_campus_id)as a on b.dorm_id = a.room_dorm_id and b.dorm_campus_id = a.room_dorm_campus_id)as c on c.dorm_id = d.dorm_id and c.campus_id = d.campus_id")
    List<DormInfo> getDormInfo();
}