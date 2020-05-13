package com.dorm.demo.dao;

import com.dorm.demo.pojo.NoticeKey;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeKeyDAO extends JpaRepository<NoticeKey, Integer> {
   //List<NoticeKey> findAll(Sort sort);
}
