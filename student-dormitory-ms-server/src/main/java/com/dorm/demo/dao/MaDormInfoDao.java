package com.dorm.demo.dao;

import com.dorm.demo.pojo.MaDormInfo;
import com.dorm.demo.pojo.MaStudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface MaDormInfoDao extends JpaRepository<MaDormInfo, Integer> {
     @Query(value = "select " +
                "new MaDormInfo(d.name,c.name,m.name)" +
                "from Campus c left join Dorm d on c.id=d.campusId left join Bed b on d.campusId=b.roomDormCampusId and b.roomDormId=d.id left join Student s on s.id=b.studentId left join DormManager m on b.roomDormCampusId=m.dormCampusId and b.roomDormId=m.dormId where m.id=:dmid")
     List<MaDormInfo> getMaDormInfo(String dmid);
}

