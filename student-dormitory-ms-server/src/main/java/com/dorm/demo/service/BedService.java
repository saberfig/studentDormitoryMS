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

    public void dormExchange(String studentId1, String studentId2){
        Bed bed1=bedDAO.findByStudentId(studentId1);
        Bed bed2=bedDAO.findByStudentId(studentId2);
        bedDAO.updateBedByStudentId(bed2.getStudentId(),bed1.getRoomId(),bed1.getRoomDormId(),bed1.getRoomDormCampusId(),bed1.getId());
        bedDAO.updateBedByStudentId(bed1.getStudentId(),bed2.getRoomId(),bed2.getRoomDormId(),bed2.getRoomDormCampusId(),bed2.getId());
    }
}
