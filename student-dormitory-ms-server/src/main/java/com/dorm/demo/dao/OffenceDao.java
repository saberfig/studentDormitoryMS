package com.dorm.demo.dao;

import com.dorm.demo.pojo.Offence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffenceDao extends JpaRepository<Offence,Integer>{

}
