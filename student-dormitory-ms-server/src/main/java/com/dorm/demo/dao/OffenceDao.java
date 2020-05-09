package com.dorm.demo.dao;

import com.dorm.demo.pojo.Offence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffenceDao extends JpaRepository<Offence,Integer>{

}
