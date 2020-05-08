package com.dorm.demo.service;

import com.dorm.demo.pojo.Student;
import com.dorm.demo.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDAO studentDAO;
    //通过id获取student表条目
    public Student getById(String id){
        return studentDAO.findById(id);
    }
    //通过id和密码获取student表条目
    public Student get(String id, String psw){
        System.out.println(id);
        System.out.println(psw);
        return studentDAO.findByIdAndPsw(id,psw);
    }
    //根据id判断student条目是否存在
    public boolean isExit(String id){
        Student student1 =getById(id);
        return null!= student1;
    }
    //添加学生项
    public void add(Student student1){
        studentDAO.save(student1);
    }
    //重置密码
    public Student resetPsw(String id){
        studentDAO.updateStudentByPsw(id);
        return studentDAO.findById(id);
    }
}
