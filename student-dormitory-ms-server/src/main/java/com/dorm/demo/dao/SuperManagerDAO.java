package com.dorm.demo.dao;

import com.dorm.demo.pojo.DormManager;
import com.dorm.demo.pojo.SuperManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SuperManagerDAO extends JpaRepository<SuperManager, Integer>{

    SuperManager findById(String id);//通过 id 字段查询到对应的行，并返回给DormManager类。

    SuperManager  findByIdAndPsw(String id, String psw);

}
