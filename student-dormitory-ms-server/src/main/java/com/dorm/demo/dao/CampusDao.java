package com.dorm.demo.dao;

import com.dorm.demo.pojo.Campus;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CampusDao extends JpaRepository<Campus, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from Campus where id=:campusId")
    void deleteByCampusId(@Param("campusId") String campusId);

    @Transactional
    @Modifying
    @Query(value = "update Campus set name=:name where id=:id")
    void modifyNameById(@Param("id") String id,@Param("name")String name);
}
