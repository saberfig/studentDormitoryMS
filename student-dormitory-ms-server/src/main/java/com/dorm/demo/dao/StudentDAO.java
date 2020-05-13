package com.dorm.demo.dao;

import com.dorm.demo.pojo.Student;

import com.dorm.demo.pojo.responsePOJO.StudentInfo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

//DAO里的用一个已经定义好的方式，通过已经和数据库链接的pojo，访问数据库里的内容，并进行操作
//定义一些简单的操作

/*
Data Access Object（数据访问对象，DAO）即用来操作数据库的对象，
这个对象可以是我们自己开发的，也可以是框架提供的。
这里我们通过继承 JpaRepository 的方式构建 DAO。
这里关键的地方在于方法的名字。
由于使用了 JPA，无需手动构建 SQL 语句，
而只需要按照规范提供方法的名字即可实现对数据库的增删改查。
findById和findByIdAndPsw不用定义，是按照某种规则命名就行？
 */
/*
Spring Data JPA框架在进行方法名解析时，会先把方法名多余的前缀截取掉，比如find，findBy，read，readBy，get，getBy，然后对剩下的部分进行解析。
假如创建如下的查询：findByUserDepUuid（），框架在解析该方法时，首先剔除findBy，然后对剩下的属性进行解析，假设查询实体为Doc
1：先判断userDepUuid（根据POJO规范，首字母变为小写）是否为查询实体的一个属性，如果是，则表示根据该属性进行查询;如果没有该属性，继续第二步;
2：从右往左截取第一个大写字母开头的字符串此处为UUID），然后检查剩下的字符串是否为查询实体的一个属性，如果是，则表示根据该属性进行查询;如果没有该属性，则重复第二步，继续从右往左截取;最后假设用户为查询实体的一个属性;
3：接着处理剩下部分（DepUuid），先判断用户所对应的类型是否有depUuid属性，如果有，则表示该方法最终是根据“Doc.user.depUuid”的取值进行查询;否则继续按照步骤2的规则从右往左截取，最终表示根据“Doc.user.dep.uuid”的值进行查询。
4：可能会存在一种特殊情况，比如Doc包含一个用户的属性，也有一个userDep属性，此时会存在混合。可以明确在属性之间加上“_”以显式表达意思，比如“findByUser_DepUuid ）“或者”findByUserDep_uuid（）"
 */
/*
关键词         样品                          JPQL片段
IsNotNull    findByAgeNotNull           ...其中x.age 不为空【年龄不为空】
喜欢          findByNameLike             ...其中x.name是什么样的？【模糊查找是......】
不喜欢        findByNameNotLike          ...其中x.name不喜欢？【模糊查找不是......】
从...开始     findByNameStartingWith      ...其中x.name类似？（参数绑定附加％）【模糊匹配，类似使用％结尾】
EndingWith   findByNameEndingWith       ...其中x.name类似于？（参数与预置％绑定）【模糊匹配，类似使用％开始】
含           findByNameContaining        ...其中x.name like？（参数绑定在％中）[模糊匹配，类似使用％开头和结尾]
排序依据       findByAgeOrderByName       ...其中x.age =？order by x.name desc 【查找后排序】
不            findByNameNot              ...其中x.name <>？【查找列不是...的】
在            findByAgeIn                ...哪里x.age在？
NotIn        findByAgeNotIn              ...其中x.age不在？
真正          findByActiveTrue            ...其中x.avtive = true
产品嫁接       findByActiveFalse           ...其中x.active = false
和           findByNameAndAge             ...其中x.name =？和x.age =？2
要么          findByNameOrAge             ...其中x.name =？或x.age =？2
之间          findBtAgeBetween            ...其中x.age之间？和？2
少于          findByAgeLessThan           ...其中x.age <？
比...更棒     findByAgeGreaterThan        ...其中x.age>？
在那之后        ...    ...
一片空白       findByAgeIsNull             ...其中x.age为空
 */
/*
 JpaRepository<T, ID>,
 T 需要类型化为实体类(Entity)User，ID需要实体类User中Id（我定义的Id类型是Long）的类型
 T ：实体类名   ID : 主键类型
 */


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



}