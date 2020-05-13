package com.dorm.demo.service;

import com.dorm.demo.dao.NoticeNormDAO;
import com.dorm.demo.pojo.NoticeNorm;
import com.dorm.demo.pojo.Offence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        System.out.println("11");
       // System.out.println(noticeNormDAO.findAllByDormManagerIdLike(dmid));
        System.out.println("111");
        return noticeNormDAO.findAllByDormManagerIdIs(dmid, Sort.by(Sort.Direction.DESC, "time"));
    }
}
