package com.dorm.demo.controller;

import com.dorm.demo.pojo.DormManagerPageRequest;
import com.dorm.demo.pojo.MaDormInfo;
import com.dorm.demo.service.MaDormInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class MaDormInfoController {
    @Autowired
    MaDormInfoService maDormInfoService;

    @CrossOrigin
    @PostMapping("api/nav_build/index")
    @ResponseBody
    public List<MaDormInfo> listbydmid(@RequestBody DormManagerPageRequest dormManagerPageRequest)throws Exception{
        String dmid = dormManagerPageRequest.getId();
        //System.out.println(dmid);
        return (maDormInfoService.findInfo(dmid));
    }
}
