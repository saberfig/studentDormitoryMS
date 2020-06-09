package com.dorm.demo.controller;

import java.io.IOException;

import com.dorm.demo.pojo.LoginForm;
import com.dorm.demo.pojo.Student;
import com.dorm.demo.pojo.DormManager;
import com.dorm.demo.pojo.SuperManager;

import com.dorm.demo.result.Result;

import com.dorm.demo.service.StudentService;
import com.dorm.demo.service.DormManagerService;
import com.dorm.demo.service.SuperManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

//Controller 是对响应进行处理的部分。
@Controller
public class LoginController {
    @Autowired
    StudentService studentService;
    @Autowired
    DormManagerService dormManagerService;
    @Autowired
    SuperManagerService superManagerService;
    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody LoginForm loginForm) {   //@RequestBody+创建存放数据的实体类+名字
        // 对 html 标签进行转义，防止 XSS 攻击
        String id = loginForm.getId();
        String status = loginForm.getStatus();
        String psw = loginForm.getPsw();
        /*
        有的时候保存到数据库时，数据库会自动将特殊字符进行转义，存到数据库的就不是你输入的那些特殊字符，而是转义以后的，例如“<”,保存到数据库时会变成“&lt;”,
        但是你想保存到数据库的就是“<”,因此你可以用HtmlUtils.htmlUnescape()进行转义一下，再保存到数据库就ok了。
         */
        System.out.println("id :"+loginForm.getId());
        System.out.println("psw :"+loginForm.getPsw());
        System.out.println("status :"+loginForm.getStatus());


        id = HtmlUtils.htmlEscape(id);
        status = HtmlUtils.htmlEscape(status);
        psw = HtmlUtils.htmlEscape(psw);

        /*
        使用数据库验证的逻辑其实也类似，大概是如下过程：
        第一步，获得前端发送过来的用户名和密码信息
        第二步，查询数据库中是否存在相同的一对用户名和密码
        第三步，如果存在，返回成功代码（200），如果不存在，返回失败代码（400）
         */

        if(status.equals("1")){
            System.out.println("student login!");
            Student student =studentService.get(id, psw);//创建一个新的实体类
            if(null== student){
                System.out.println("not find      00000000000");
                return new Result(400);//返回不同的结果
            }else{
                System.out.println("find  0000000000000");
                return new Result(200,student.getName());
            }
        }
        else if (status.equals("2")){
            System.out.println("dorm manager login!");
            DormManager dormManager =dormManagerService.get(id, psw);//创建一个新的实体类
            if(null== dormManager){
                System.out.println("not find      111111111111");
                return new Result(400);//返回不同的结果
            }else{
                System.out.println("find  111111111111");
                return new Result(200,dormManager.getName());
            }
        }
        else if (status.equals("3")){
            System.out.println("super manager login");
            SuperManager superManager =superManagerService.get(id, psw);//创建一个新的实体类
            if(null== superManager){
                System.out.println("not find      222222222222");
                return new Result(400);//返回不同的结果
            }else{
                System.out.println("find  2222222222222");
                return new Result(200,superManager.getName());
            }
        }
        else{
            System.out.println("err");
            return new Result(400);//返回不同的结果
        }
    }
}

