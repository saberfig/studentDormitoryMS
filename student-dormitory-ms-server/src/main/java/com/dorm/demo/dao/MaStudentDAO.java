package com.dorm.demo.dao;


import com.dorm.demo.pojo.MaStudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaStudentDAO extends JpaRepository<MaStudentInfo, Integer>{
    @Query(value = "select " +
            "new MaStudentInfo(s.id,s.name,b.roomId,s.sex,s.major,s.tel)" +
            "from Student s left join Bed b on b.studentId=s.id left join DormManager d on d.dormId=b.roomDormId and d.dormCampusId = b.roomDormCampusId where d.id=:dmid")
    List<MaStudentInfo> getMaStudentInfo(String dmid);
}
