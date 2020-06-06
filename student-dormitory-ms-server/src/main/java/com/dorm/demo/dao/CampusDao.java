package com.dorm.demo.dao;

import com.dorm.demo.pojo.Campus;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CampusDao extends JpaRepository<CampusInfo, Integer> {

    @Query(nativeQuery=true,value = "" +
            "select campus_id as id,name,dorm_num,room_num,bed_num from (select dorm_num,room_num,campus_id,bed_num from (select dorm_num,room_num,campus_id from (select count(*) as dorm_num,campus_id from dorm group by campus_id)as a left join (select count(*) as room_num,dorm_campus_id from room group by dorm_campus_id) as b on b.dorm_campus_id = a.campus_id)as d left join (select count(*)as bed_num,room_dorm_campus_id from bed group by room_dorm_campus_id)as c on d.campus_id =c.room_dorm_campus_id)as e left join (select * from campus)as f on f.id = e.campus_id")
    List<CampusInfo> getCampusInfo();
}
