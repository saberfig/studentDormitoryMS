package com.dorm.demo.controller;


import com.dorm.demo.pojo.Offence;
import com.dorm.demo.service.OffenceService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OffenceController {
    @Autowired
    OffenceService offenceService;

    @PostMapping("api/nav_build/jiluweiji")
    public Offence addOrUpdate(@RequestBody Offence offence)throws Exception{
        offenceService.addOrUpdate(offence);
        return offence;
    }
}
