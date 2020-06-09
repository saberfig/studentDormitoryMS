package com.dorm.demo.dao;

import com.dorm.demo.pojo.MaVinfo;
import com.dorm.demo.pojo.NoticeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaVinfoDAO extends JpaRepository<MaVinfo, Integer> {

    @Query(value = "select " +
            "new MaVinfo(v.id, v.timeIn, v.vname, v.videntity, v.reason, v.studentId)" +
            "from VisitorVinfo v left join Student s on v.studentId=s.id left join Bed b on b.studentId=s.id left join DormManager d on d.dormId=b.roomDormId and d.dormCampusId = b.roomDormCampusId where d.id=:dmid")
    List<MaVinfo> getVisitorVinfo(String dmid);
}
