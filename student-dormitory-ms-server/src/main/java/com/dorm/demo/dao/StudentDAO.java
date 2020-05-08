package com.dorm.demo.dao;

import com.dorm.demo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentDAO extends JpaRepository<Student, Integer>{
    Student findById(String id);

    Student findByIdAndPsw(String id, String psw);

    @Transactional
    @Modifying
    @Query(value = "update Student set psw=:id where id=:id")
    void updateStudentByPsw(@Param("id") String id);
}