package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.Student_Bed;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import com.dorm.demo.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CampusInfoController {
    @Autowired
    CampusService campusService;

    @CrossOrigin
    @GetMapping(value = "api/manager/get_campus_info")
    @ResponseBody
    public List<CampusInfo> getStudent_Bed(){
        return campusService.getCampusInfo();
    }
}
