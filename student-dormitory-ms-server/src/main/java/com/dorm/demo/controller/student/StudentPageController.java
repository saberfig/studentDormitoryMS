package com.dorm.demo.controller.student;

import com.dorm.demo.dao.StudentDAO;
import com.dorm.demo.pojo.StudentPageRequest;
import com.dorm.demo.pojo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.dorm.demo.pojo.studentresponse.StudentInfo;

import com.dorm.demo.service.StudentService;

@Controller
public class StudentPageController {
    @Autowired
    StudentService studentService;
    //

    @CrossOrigin
    @PostMapping(value = "api/studentinfo")
    @ResponseBody
    public StudentInfo info(@RequestBody StudentPageRequest studentPageRequest) {   //@RequestBody+创建存放数据的实体类+名字

        String id = studentPageRequest.getId();
        System.out.println("StudentInfo function");
        System.out.println("id :"+id);

        id = HtmlUtils.htmlEscape(id);

        Student student =studentService.getById(id);//创建一个新的实体类

        //StudentInfo studentInfo = new StudentInfo("沙河校区","2","227","2");
        StudentInfo studentInfo = studentService.getStudentInfoById(id);

        return studentInfo;
    }


}
