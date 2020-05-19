package com.dorm.demo.controller.manager;

import com.dorm.demo.pojo.responsePOJO.DormInfo;
import com.dorm.demo.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
