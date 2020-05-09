package com.dorm.demo.service;

import com.dorm.demo.pojo.Student;
import com.dorm.demo.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
这里实际上是对 StudentDAO 进行了二次封装，
一般来讲，我们在 DAO 中只定义基础的增删改查操作，而具体的操作，需要由 Service 来完成。
当然，由于我们做的操作原本就比较简单，所以这里看起来只是简单地重命名了一下，比如把 “通过用户名及密码查询并获得对象” 这种方法命名为 get。

 */

@Service
public class StudentService {
    @Autowired
    StudentDAO studentDAO;//创建一个Data Access Object（数据访问对象，DAO）
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
    public Student getNameById(String id){
        return studentDAO.getStudentName(id);
    }
}
