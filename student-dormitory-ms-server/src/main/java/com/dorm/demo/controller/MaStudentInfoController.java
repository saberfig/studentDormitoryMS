package com.dorm.demo.controller;


import com.dorm.demo.pojo.DormManagerPageRequest;
import com.dorm.demo.pojo.MaStudentInfo;
import com.dorm.demo.service.MaStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class MaStudentInfoController {

    @Autowired
    MaStudentInfoService maStudentInfoService;

    @CrossOrigin
    @PostMapping("api/nav_build/chuangwei_build")
    @ResponseBody
    public List<MaStudentInfo> listbydmid(@RequestBody DormManagerPageRequest dormManagerPageRequest)throws Exception{
        String dmid = dormManagerPageRequest.getId();
        //System.out.println(dmid);
        return (maStudentInfoService.findInfo(dmid));
    }


}
