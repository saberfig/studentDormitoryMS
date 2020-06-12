package com.dorm.demo.dao;

import com.dorm.demo.pojo.Student;

import com.dorm.demo.pojo.responsePOJO.StudentInfo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Integer>{

    Student findById(String id);//通过 id 字段查询到对应的行，并返回给 User 类。

    Student findByIdAndPsw(String id, String psw);

    @Transactional
    @Modifying
    @Query(value = "update Student set psw=:id where id=:id")
    void updateStudentByPsw(@Param("id") String id);

    @Query(value = "select name from Student where id=:id")
    Student getStudentName(@Param("id") String id);
    /*
    @Query(value = "select s.name from Student s left join Bed b on b.studentId=s.id left join Dorm d on d.id=b.roomDormId left join Campus c on c.id=b.roomDormCampusId where s.id=:id")
    List<Object> getStudentInfo(@Param("id") String id);
    */
    @Query(value = "select "+
            "new StudentInfo(c.name,d.id,r.id,b.id) "+
            "from Student s left join Bed b on b.studentId=s.id left join Room r on r.id=b.roomId left join Dorm d on d.id=b.roomDormId left join Campus c on c.id=b.roomDormCampusId where s.id=:id")
    List<StudentInfo> getStudentInfo(@Param("id") String id);

    /*
    @Query(value = "select "+
            "new StudentNotice(n.time, n.description) "+
            "from Student s left join Bed b on b.studentId=s.id left join DormManager d on d.dormId=b.roomDormId left join NoticeNorm n on n.dormManagerId=d.id where s.id=:id")
    List<StudentNotice> getStudentDormNotice(@Param("id") String id);
    */

    @Transactional
    @Modifying
    @Query(value = "update Student set psw=:newpsw where id=:id")
    void changeStudentByPsw(@Param("id") String id, @Param("newpsw") String newpsw);

    @Transactional
    @Modifying
    @Query(value = "delete from Student where id=:id")
    void deleteByStudentId(@Param("id") String id);


}