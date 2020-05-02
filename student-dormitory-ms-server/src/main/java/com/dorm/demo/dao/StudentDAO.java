package com.dorm.demo.dao;

import com.dorm.demo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Integer>{
    Student findById(String id);
    Student findByIdAndPsw(String id, String psw);
}
