package com.dorm.demo.controller.student;

import com.dorm.demo.pojo.StudentPageRequest;
import com.dorm.demo.pojo.Student;
import com.dorm.demo.pojo.ChangePswForm;

import com.dorm.demo.pojo.responsePOJO.StudentNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.dorm.demo.pojo.responsePOJO.StudentInfo;

import com.dorm.demo.service.StudentService;

import com.dorm.demo.result.Result;


import java.util.List;

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

    @CrossOrigin
    @PostMapping(value = "api/studentnotice")
    @ResponseBody
    public List<StudentNotice> notice(@RequestBody StudentPageRequest studentPageRequest) {   //@RequestBody+创建存放数据的实体类+名字

        String id = studentPageRequest.getId();
        System.out.println("StudentInfo function");
        System.out.println("id :"+id);

        id = HtmlUtils.htmlEscape(id);

        Student student =studentService.getById(id);//创建一个新的实体类

        //StudentInfo studentInfo = new StudentInfo("沙河校区","2","227","2");
        List<StudentNotice> Studentnotice = studentService.getStudentNoticeByIdDiff(id);

        return Studentnotice;
    }

    @CrossOrigin
    @PostMapping(value = "api/changepsw")
    @ResponseBody
    public Result changepsw(@RequestBody ChangePswForm changePswForm) {   //@RequestBody+创建存放数据的实体类+名字

        String id = changePswForm.getId();
        String oldpsw = changePswForm.getOldpsw();
        String newpsw = changePswForm.getNewpsw();
        System.out.println("changepsw function");
        System.out.println("id :"+id);
        System.out.println("oldpsw :"+oldpsw);
        System.out.println("newpsw :"+newpsw);

        id = HtmlUtils.htmlEscape(id);
        oldpsw = HtmlUtils.htmlEscape(oldpsw);
        newpsw = HtmlUtils.htmlEscape(newpsw);

        Student student =studentService.getById(id);//创建一个新的实体类

        if(!student.getPsw().equals(oldpsw)){
            return new Result(400,student.getName());
        }
        else{
            studentService.changePsw(id, newpsw);
            return new Result(200,student.getName());
        }

    }


}
