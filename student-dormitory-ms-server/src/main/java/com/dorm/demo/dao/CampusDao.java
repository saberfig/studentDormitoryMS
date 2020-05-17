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
    //使用原生MySQL查询时，返回Object对象，或者Object[]
    //@Query(nativeQuery=true,value = "select " +
    //        "c.id,c.name,c.dormNum,c.roomNum,d.bedNum" +
    //        "from (select a.row_number,a.id,a.name,a.dormNum,b.roomNum from " +
    //        "(select @row1\\:=@row1 + 1 as row_number ,t.* from (select campus.id,campus.name,COUNT(campus.id) as dormNum from dorm left join campus on dorm.campus_id=campus.id group by campus.id) t ,(select @row1\\:=0) r) a " +
    //        "left join " +
    //        "(select @row2\\:=@row2 + 1 as row_number ,t.* from (select COUNT(dorm_campus_id) as roomNum from room  group by dorm_campus_id) t ,(select @row2\\:=0) r) b " +
    //        "on a.row_number=b.row_number) c " +
    //        "left join " +
    //        "(select @row3\\:=@row3 + 1 as row_number ,t.* from (select COUNT(room_dorm_campus_id) as bedNum from bed group by room_dorm_campus_id) t ,(select @row3\\:=0) r) d " +
    //        "on c.row_number=d.row_number")
    //List<CampusInfo> getCampusInfo();
    @Query(nativeQuery=true,value = "" +
            "select c.id,c.name,c.dorm_num,c.room_num,d.bed_num from " +
            "(select a.row_number,a.id,a.name,a.dorm_num,b.room_num from " +
            "(select @row1\\:=@row1 + 1 as row_number ,t.* from " +
            "(select campus.id,campus.name,COUNT(campus.id) as dorm_num from " +
            "dorm left join campus on dorm.campus_id=campus.id group by campus.id) t ,(select @row1\\:=0) r) a " +
            "left join " +
            "(select @row2\\:=@row2 + 1 as row_number ,t.* from (select COUNT(dorm_campus_id) as room_num from room  group by dorm_campus_id) t ,(select @row2\\:=0) r) b  on a.row_number=b.row_number) c " +
            "left join " +
            "(select @row3\\:=@row3 + 1 as row_number ,t.* from (select COUNT(room_dorm_campus_id) as bed_num from bed group by room_dorm_campus_id) t ,(select @row3\\:=0) r) d " +
            "on c.row_number=d.row_number;")
    List<CampusInfo> getCampusInfo();
}
