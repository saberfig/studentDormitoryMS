package com.dorm.demo.service;

import com.dorm.demo.pojo.Student;
import com.dorm.demo.dao.StudentDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDAO studentDAO;

    public Student getById(String id){
        return studentDAO.findById(id);
    }

    public Student get(String id,String psw){
        System.out.println(id);
        System.out.println(psw);
        return studentDAO.findByIdAndPsw("liu","liu");
    }

    public boolean isExit(String id){
        Student student=getById(id);
        return null!=student;
    }

    public void add(Student student){
        studentDAO.save(student);
    }
}
