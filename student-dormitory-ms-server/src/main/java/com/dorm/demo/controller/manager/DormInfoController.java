package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.Dorm;
import com.dorm.demo.pojo.responsePOJO.DormInfo;
import com.dorm.demo.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DormInfoController {
    @Autowired
    DormService dormService;

    @CrossOrigin
    @GetMapping(value = "api/manager/get_dorm_info")
    @ResponseBody
    public List<DormInfo> getStudent_Bed(){
        return dormService.getDormInfo();
    }

    @CrossOrigin
    @PostMapping("api/manager/add_dorm")
    @ResponseBody
    public void addDorm(@RequestBody Dorm dorm){
        dormService.addDorm(dorm);
    }

    @CrossOrigin
    @PostMapping("api/manager/del_dorm")
    @ResponseBody
    public void deleteDorm(@RequestBody Dorm dorm){
        dormService.deleteDorm(dorm);
    }

    @CrossOrigin
    @PostMapping("api/manager/modify_dorm")
    @ResponseBody
    public void modifyDorm(@RequestBody Dorm dorm){
        dormService.modifyDorm(dorm);
    }
}
