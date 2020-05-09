package com.dorm.demo.dao;

import com.dorm.demo.pojo.NoticeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeKeyDAO extends JpaRepository<NoticeKey, Integer> {
   //List<NoticeKey> findAllByIdIsNotNull();
}
