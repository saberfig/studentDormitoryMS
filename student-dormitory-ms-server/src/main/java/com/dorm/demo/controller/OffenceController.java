package com.dorm.demo.controller;


import com.dorm.demo.pojo.Offence;
import com.dorm.demo.service.OffenceService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
