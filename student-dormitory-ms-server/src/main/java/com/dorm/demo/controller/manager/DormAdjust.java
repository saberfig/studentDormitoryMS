package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.Bed;
import com.dorm.demo.pojo.Student_Bed;
import com.dorm.demo.result.Result;
import com.dorm.demo.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DormAdjust {
    @Autowired
    BedService bedService;

    @CrossOrigin
    @GetMapping(value = "api/manager/get_student_bed")
    @ResponseBody
    public List<Student_Bed> getStudent_Bed(){
        return bedService.getStudent_Bed();
    }

    @CrossOrigin
    @PostMapping(value = "api/manager/del_bed")
    @ResponseBody
    public Result deleteBedByStudentId(@RequestBody Bed requestBed){
        bedService.deleteByStudentId(requestBed.getStudentId());
        if(null==bedService.findByStudentId(requestBed.getStudentId())){
            return new Result(200);
        }else{
            return new Result(400);
        }
    }

    @CrossOrigin
    @PostMapping(value = "api/manager/add_bed")
    @ResponseBody
    public Result addBed(@RequestBody Bed requestBed){
        Bed bed=bedService.findByStudentId(requestBed.getStudentId());
        if(null==bed){
            bedService.addBed(requestBed);
            bed=bedService.findByStudentId(requestBed.getStudentId());
            if(null==bed){
                return new Result(400);//添加失败
            }else return new Result(200);//添加成功
        }else{
            return new Result(401);//学生已存在
        }
    }
}
