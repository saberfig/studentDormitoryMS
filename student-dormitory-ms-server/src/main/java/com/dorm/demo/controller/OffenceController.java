package com.dorm.demo.controller;


import com.dorm.demo.pojo.DormManagerPageRequest;
import com.dorm.demo.pojo.OffenInfo;
import com.dorm.demo.pojo.Offence;
import com.dorm.demo.service.OffenceService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RestController
public class OffenceController {
    @Autowired
    OffenceService offenceService;

    @CrossOrigin
    @PostMapping("api/nav_build/jiluweiji")
    @ResponseBody
    public Offence add(@RequestBody Offence offence)throws Exception{
        System.out.println(offence.getStudentId());
        offenceService.add(offence);
        return offence;
    }

    @CrossOrigin
    @PostMapping("api/nav_build/xsjiluweiji")
    @ResponseBody
    public List<OffenInfo> listbydmid(@RequestBody DormManagerPageRequest dormManagerPageRequest)throws Exception{
        String dmid = dormManagerPageRequest.getId();
        //System.out.println(dmid);
        return (offenceService.findInfo(dmid));
    }
}
