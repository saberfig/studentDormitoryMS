package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.Bed;
import com.dorm.demo.pojo.NoticeNorm;
import com.dorm.demo.pojo.Student_Bed;
import com.dorm.demo.pojo.Campus;
import com.dorm.demo.pojo.responsePOJO.CampusInfo;
import com.dorm.demo.result.Result;
import com.dorm.demo.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping("api/manager/add_campus")
    @ResponseBody
    public String addCampus(@RequestBody Campus campus) {
        return campusService.addCampus(campus);
    }

    @CrossOrigin
    @PostMapping("api/manager/del_campus")
    @ResponseBody
    public void delCampus(@RequestBody Campus campus) {
        campusService.deleteById(campus.getId());
    }

    @CrossOrigin
    @PostMapping("api/manager/modify_campus")
    @ResponseBody
    public void modifyCampus(@RequestBody Campus campus) {
        campusService.modifyNameById(campus.getId(),campus.getName());
    }
}
