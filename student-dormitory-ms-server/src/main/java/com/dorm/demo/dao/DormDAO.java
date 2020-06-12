package com.dorm.demo.dao;

import com.dorm.demo.pojo.Dorm;
import com.dorm.demo.pojo.responsePOJO.DormInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DormDAO extends JpaRepository<Dorm, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from Dorm where id=:id and campusId=:campusId")
    void deleteByIdAndCampusId(@Param("id") String id,@Param("campusId") String campusId);

    @Transactional
    @Modifying
    @Query(value = "update Dorm set name=:name where id=:id and campusId=:campusId")
    void modifyNameByIdAndCampusId(@Param("id") String id,@Param("campusId") String campusId,@Param("name")String name);
}