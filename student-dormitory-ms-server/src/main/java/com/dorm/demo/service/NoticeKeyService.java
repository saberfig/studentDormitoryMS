package com.dorm.demo.service;

import com.dorm.demo.dao.NoticeKeyDAO;

import com.dorm.demo.pojo.NoticeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeKeyService {
    @Autowired
    NoticeKeyDAO noticeKeyDAO;

    public List<NoticeKey> list() {
        return noticeKeyDAO.findAll(Sort.by(Sort.Direction.DESC, "time"));
    }
}
