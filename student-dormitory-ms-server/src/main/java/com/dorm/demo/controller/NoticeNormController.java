package com.dorm.demo.controller;

import com.dorm.demo.pojo.NoticeNorm;
import com.dorm.demo.pojo.DormManagerPageRequest;
import com.dorm.demo.service.NoticeNormService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
@RestController

public class NoticeNormController {
    @Autowired
    NoticeNormService noticeNormService;

    @CrossOrigin
    @PostMapping("api/nav_build/xsxuetz")
    @ResponseBody
    public List<NoticeNorm> listbydmid(@RequestBody DormManagerPageRequest dormManagerPageRequest)throws Exception{
        String dmid = dormManagerPageRequest.getId();
        //dmid = HtmlUtils.htmlEscape(dmid);
        System.out.println(dmid);
        return (noticeNormService.listbydmid(dmid));
    }
//    @CrossOrigin
//    @GetMapping("api/nav_build/xsxuetz")
//    @ResponseBody
//    public List<NoticeNorm> listbydmid()throws Exception{
//        String dmid="2017211862";
//        //System.out.println(dmid);
//        return (noticeNormService.listbydmid(dmid));
//    }
    @CrossOrigin
    @PostMapping("api/nav_build/xuetz")
    @ResponseBody
    public NoticeNorm add(@RequestBody NoticeNorm noticeNorm)throws Exception{
        //System.out.println(noticeNorm.getStudentId());
        noticeNormService.add(noticeNorm);
    return noticeNorm;

    }


}
