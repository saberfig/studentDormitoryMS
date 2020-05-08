package com.dorm.demo.service;

import com.dorm.demo.dao.BedDAO;
import com.dorm.demo.pojo.Bed;
import com.dorm.demo.pojo.Student_Bed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {
    @Autowired
    BedDAO bedDAO;
    //获取学生和床的内连接的表
    public List<Student_Bed> getStudent_Bed(){
        return bedDAO.getStudent_Bed();
    }
    //根据学生id删除bed表条目
    public void deleteByStudentId(String studentId){
        bedDAO.deleteByStudentId(studentId);
    }
    //根据学生id查找bed表条目
    public Bed findByStudentId(String studentId){
        return bedDAO.findByStudentId(studentId);
    }
    //添加bed
    public void addBed(Bed bed){
        bedDAO.save(bed);
    }
}
