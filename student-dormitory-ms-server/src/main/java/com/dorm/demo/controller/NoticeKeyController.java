package com.dorm.demo.controller;

import com.dorm.demo.pojo.NoticeKey;
import com.dorm.demo.service.NoticeKeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeKeyController {

    @Autowired
    NoticeKeyService noticeKeyService;

    @CrossOrigin
    @GetMapping(value = "api/nav_build/xiaotz")

    public List<NoticeKey> list() throws Exception{
        return noticeKeyService.list();
    }
}
