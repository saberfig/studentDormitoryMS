package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.Student;
import com.dorm.demo.pojo.Student_Bed;
import com.dorm.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentInfo {
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @PostMapping(value = "api/manager/reset_psw")
    @ResponseBody
    public Student resetPsw(@RequestBody Student requestStudent){
        return studentService.resetPsw(requestStudent.getId());
    }

}
