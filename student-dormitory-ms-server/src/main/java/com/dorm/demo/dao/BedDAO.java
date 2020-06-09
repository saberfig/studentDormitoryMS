package com.dorm.demo.dao;

import com.dorm.demo.pojo.Bed;
import com.dorm.demo.pojo.Student_Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BedDAO extends JpaRepository<Bed, Integer> {

    @Query(value = "select " +
            "new Student_Bed(s.id,s.name,b.id,b.roomId,b.roomDormId,b.roomDormCampusId)" +
            "from Student s inner join Bed b on s.id=b.studentId")
    List<Student_Bed> getStudent_Bed();

    @Transactional
    @Modifying
    @Query(value = "delete from Bed where studentId=:studentId")
    void deleteByStudentId(@Param("studentId") String studentId);

   Bed findByStudentId(String studentId);

    @Transactional
    @Modifying
    @Query(value = "update Bed set studentId=:studentId where id=:id and roomId=:roomId and roomDormId=:roomDormId and roomDormCampusId=:roomDormCampusId")
    void updateBedByStudentId(@Param("studentId") String studentId,
                              @Param("roomId") String roomId,
                              @Param("roomDormId") String roomDormId,
                              @Param("roomDormCampusId") String roomDormCampusId,
                              @Param("id") String id);
}
