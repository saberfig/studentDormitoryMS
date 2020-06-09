package com.dorm.demo.service;

import com.dorm.demo.dao.DormManagerDAO;
import com.dorm.demo.dao.NoticeNormDAO;
import com.dorm.demo.dao.StudentDAO;
import com.dorm.demo.dao.BedDAO;
import com.dorm.demo.pojo.Bed;
import com.dorm.demo.pojo.DormManager;
import com.dorm.demo.pojo.NoticeNorm;
import com.dorm.demo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorm.demo.pojo.responsePOJO.StudentInfo;
import com.dorm.demo.pojo.responsePOJO.StudentNotice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/*
这里实际上是对 StudentDAO 进行了二次封装，
一般来讲，我们在 DAO 中只定义基础的增删改查操作，而具体的操作，需要由 Service 来完成。
当然，由于我们做的操作原本就比较简单，所以这里看起来只是简单地重命名了一下，比如把 “通过用户名及密码查询并获得对象” 这种方法命名为 get。

 */

@Service
public class StudentService {
    @Autowired
    StudentDAO studentDAO;//创建一个Data Access Object（数据访问对象，DAO）

    @Autowired
    BedDAO bedDAO;//创建一个Data Access Object（数据访问对象，DAO）

    @Autowired
    DormManagerDAO dormManagerDAO;//创建一个Data Access Object（数据访问对象，DAO）

    @Autowired
    NoticeNormDAO noticeNormDAO;//创建一个Data Access Object（数据访问对象，DAO）

    //通过id获取student表条目
    public Student getById(String id){
        return studentDAO.findById(id);
    }


    //通过id和密码获取student表条目
    public Student get(String id, String psw){
        System.out.println("传入service搜索的id："+id);
        System.out.println("传入service搜索的psw:"+psw);
        return studentDAO.findByIdAndPsw(id,psw);
    }


    //根据id判断student条目是否存在
    public boolean isExit(String id){
        Student student1 =getById(id);
        return null!= student1;
    }


    //添加学生项
    public void add(Student student1){
        studentDAO.save(student1);
    }
    //重置密码
    public Student resetPsw(String id){
        studentDAO.updateStudentByPsw(id);
        return studentDAO.findById(id);
    }
    public Student getNameById(String id){
        return studentDAO.getStudentName(id);
    }

    public void changePsw(String id, String newpsw){
        studentDAO.changeStudentByPsw(id, newpsw);

    }


    public StudentInfo getStudentInfoById(String id){
        List<StudentInfo> queryResult = studentDAO.getStudentInfo(id);
        StudentInfo studentInfo=queryResult.get(0);
        System.out.println("校区名：“"+studentInfo.getCampusname());
        System.out.println("宿舍号："+studentInfo.getCampusname());
        System.out.println("房间号："+studentInfo.getCampusname());
        System.out.println("床号："+studentInfo.getCampusname());
        return studentInfo;
    }

    /*
    public List<StudentNotice> getStudentNoticeById(String id){
        System.out.println("进入service层，准备开始查找");
        List<StudentNotice> queryResult = studentDAO.getStudentDormNotice(id);
        System.out.println("处于service层，查找结束");
        System.out.println(queryResult.get(0));
        //Collections.sort(queryResult, new DateComparator());
        return queryResult;
    }


     */
    public List<StudentNotice> getStudentNoticeByIdDiff(String id){
        System.out.println("进入service层，准备开始麻烦的查找");
        Student student = studentDAO.findById(id);
        Bed bed = bedDAO.findByStudentId(id);
        String dormNum =  bed.getRoomDormId();
        List<DormManager> dormManagers = dormManagerDAO.findAllByDormId(dormNum);
        List<StudentNotice> studentNotice = new ArrayList<StudentNotice>();;
        for(int i=0;i<dormManagers.size();i++){
            String dormManagerId = dormManagers.get(i).getId();
            List<NoticeNorm> noticeNorms = noticeNormDAO.findAllByDormManagerIdLike(dormManagerId);
            for(int j = 0; j< noticeNorms.size(); j++){
                String content = noticeNorms.get(j).getDescription();
                Timestamp date = noticeNorms.get(j).getTime();
                System.out.println("内容"+content);
                System.out.println("日期"+date);
                studentNotice.add(new StudentNotice(content,date));
            }
        }

        Collections.sort(studentNotice, new DateComparator());
        for(int i=0;i<studentNotice.size();i++){
            studentNotice.get(i).Trans();
        }

        System.out.println("处于service层，查找结束");
        System.out.println(studentNotice.get(0));
        //Collections.sort(queryResult, new DateComparator());

        return studentNotice;
    }

    static class DateComparator implements Comparator<StudentNotice> {
        public int compare(StudentNotice object1, StudentNotice object2) {// 实现接口中的方法
            Timestamp date1 = object1.getDate();
            Timestamp date2 = object2.getDate();
            /*
            关于返回值
            也就是说当语句return s1.age > s2.age ? 1 : -1;的返回值为1时，也就是说 s1的值大于s2的值时 ，compareTo是按照升序（由小到大）排序的！
            当返回值为-1时，也就是说 s1的值小于s2的值时 ，compareTo是按照降序（由大到小）排序的！
             */
            if(date1.before(date2)){
                return 1;
            }
            else{
                return -1;
            }
        }

    }

}
