package com.dorm.demo.controller.manager;
import com.dorm.demo.pojo.Room;
import com.dorm.demo.pojo.responsePOJO.RoomInfo;
import com.dorm.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @PostMapping("api/manager/add_room")
    @ResponseBody
    public void addRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @CrossOrigin
    @PostMapping("api/manager/del_room")
    @ResponseBody
    public void deleteRoom(@RequestBody Room room){
        roomService.deleteBy3Id(room);
    }
}
