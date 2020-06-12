package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.Student;
import com.dorm.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentInfoController {
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @PostMapping(value = "api/manager/reset_psw")
    @ResponseBody
    public Student resetPsw(@RequestBody Student requestStudent){
        return studentService.resetPsw(requestStudent.getId());
    }

    @CrossOrigin
    @PostMapping("api/manager/modify_student")
    @ResponseBody
    public void modifyStudent(@RequestBody Student student){
        studentService.add(student);
    }

    @CrossOrigin
    @PostMapping("api/manager/del_student")
    @ResponseBody
    public void deleteStudent(@RequestBody Student student){
        studentService.deleteByStudentId(student.getId());
    }

    @CrossOrigin
    @PostMapping("api/manager/add_student")
    @ResponseBody
    public void addStudent(@RequestBody Student student){
        studentService.add(student);
    }

    @CrossOrigin
    @GetMapping("api/manager/get_student")
    @ResponseBody
    public List<Student> getStudent(){
        return studentService.getAllStudent();
    }


}
