package com.dorm.demo.service;

import com.dorm.demo.dao.NoticeNormDAO;
import com.dorm.demo.pojo.NoticeNorm;
import com.dorm.demo.pojo.Offence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class NoticeNormService {

    @Autowired
    NoticeNormDAO noticeNormDAO;

    public void add(NoticeNorm noticeNorm){

        noticeNormDAO.save(noticeNorm);
    }

    public List<NoticeNorm> listbydmid(String dmid){
        return noticeNormDAO.findAllByDormManagerIdLike(dmid);
    }
}
