package com.dorm.demo.controller;


import com.dorm.demo.pojo.DormManagerPageRequest;
import com.dorm.demo.pojo.MaVinfo;
import com.dorm.demo.pojo.VisitorVinfo;
import com.dorm.demo.service.MaVinfoService;
import com.dorm.demo.service.VisitorVinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    MaVinfoService maVinfoService;
    @CrossOrigin
    @PostMapping("api/nav_build/xsfangkexinxi")
    @ResponseBody
    public List<MaVinfo> listbydmid(@RequestBody DormManagerPageRequest dormManagerPageRequest)throws Exception{
        String dmid = dormManagerPageRequest.getId();
        //System.out.println(dmid);
        return (maVinfoService.findInfo(dmid));
    }

}
