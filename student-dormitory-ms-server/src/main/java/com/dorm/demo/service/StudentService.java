package com.dorm.demo.service;

import com.dorm.demo.pojo.Student;
import com.dorm.demo.dao.StudentDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDAO studentDAO;

    public Student getById(String id){
        return studentDAO.findById(id);
    }

    public Student get(String id, String psw){
        System.out.println(id);
        System.out.println(psw);
        return studentDAO.findByIdAndPsw(id,psw);
    }

    public boolean isExit(String id){
        Student student1 =getById(id);
        return null!= student1;
    }

    public void add(Student student1){
        studentDAO.save(student1);
    }
}
