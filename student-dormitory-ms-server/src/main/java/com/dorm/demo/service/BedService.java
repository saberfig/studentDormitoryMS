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

    public void exchangeBed(List<Bed> beds){
        String id1=beds.get(0).getStudentId();
        String id2=beds.get(1).getStudentId();
        Bed bed1 = bedDAO.findByStudentId(id1);
        bed1.setStudentId(id2);
        Bed bed2 = bedDAO.findByStudentId(id2);
        bed2.setStudentId(id1);
        bedDAO.save(bed1);
        bedDAO.save(bed2);
    }
}
