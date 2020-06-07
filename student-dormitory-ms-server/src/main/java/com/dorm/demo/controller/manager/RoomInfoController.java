package com.dorm.demo.controller.manager;
import com.dorm.demo.pojo.responsePOJO.RoomInfo;
import com.dorm.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoomInfoController {

    @Autowired
    RoomService roomService;

    @CrossOrigin
    @GetMapping(value = "api/manager/get_room_info")
    @ResponseBody
    public List<RoomInfo> getStudent_Bed(){

        System.out.println(roomService.getRoomInfo());
        return roomService.getRoomInfo();
    }
}
