package com.dorm.demo.controller;

import com.dorm.demo.result.Result;
import com.dorm.demo.pojo.Student;
import com.dorm.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {
    @Autowired
    StudentService studentService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody Student requestStudent) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String id = requestStudent.getId();
        id = HtmlUtils.htmlEscape(id);

        Student student =studentService.get(id, requestStudent.getPsw());
        if(null== student){
            return new Result(400);
        }else{
            return new Result(200);
        }
    }
}

