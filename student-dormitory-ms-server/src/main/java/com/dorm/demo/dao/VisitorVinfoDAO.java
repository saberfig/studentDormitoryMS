package com.dorm.demo.dao;


import com.dorm.demo.pojo.VisitorVinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitorVinfoDAO extends JpaRepository<VisitorVinfo, Integer> {

}
