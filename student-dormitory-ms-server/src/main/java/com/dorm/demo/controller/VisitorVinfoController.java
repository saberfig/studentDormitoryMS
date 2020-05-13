package com.dorm.demo.controller;


import com.dorm.demo.pojo.VisitorVinfo;
import com.dorm.demo.service.VisitorVinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class VisitorVinfoController {
    @Autowired
    VisitorVinfoService visitorVinfoService;

    @CrossOrigin
    @PostMapping("api/nav_build/djfangkexinxi")
    @ResponseBody
    public VisitorVinfo add(@RequestBody VisitorVinfo visitorVinfo)throws Exception{
        visitorVinfoService.add(visitorVinfo);
        return visitorVinfo;
    }

}
