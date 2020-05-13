package com.dorm.demo.dao;


import com.dorm.demo.pojo.NoticeNorm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeNormDAO extends JpaRepository<NoticeNorm, Integer>{
    List<NoticeNorm> findAllByDormManagerIdLike(String dmid);
    List<NoticeNorm> findAllByDormManagerIdIs(String dmid, Sort sort);
}
