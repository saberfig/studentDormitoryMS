package com.dorm.demo.dao;

import com.dorm.demo.pojo.Offence;
import com.dorm.demo.pojo.OffenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OffenceDao extends JpaRepository<Offence,Integer>{
    @Query(value = "select " +
            "new OffenInfo(o.id,s.name,s.id,o.description)" +
            "from Offence o left join Student s on o.studentId=s.id left join Bed b on b.studentId=s.id left join DormManager d on d.dormId=b.roomDormId and d.dormCampusId = b.roomDormCampusId where d.id=:dmid")
    List<OffenInfo> getOffenInfo(String dmid);
}
