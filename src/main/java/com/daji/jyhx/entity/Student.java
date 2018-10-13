package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/915:30
 */
//学生表
@Entity
@Data
public class Student {

    //学生id (学籍) 主键
    @Id
    @Column(length = 32)
    private String studentId;

    //学生密码
    private String studentPassword;

    //学生姓名
    private String studentName;

    //学生性别
    private Integer studentSex;

    //学生手机号
    private String studentTel;

    //学生所属班级id 外键 多对一 一个班级有多个学生
//    @ManyToOne
//    @JoinColumn(name = "student_clazz_id")
//    private Clazz clazz;
    private String studentClazzId;

    //学生所属年级id 外键 多对一 一个年级有多个学生
//    @ManyToOne
//    @JoinColumn(name = "student_grade_id")
//    private Grade grade;
    private String studentGradeId;

    //学生邮箱
    private String studentEmail;

    //学生生日
    private Date studentBirthday;

    //学生住址
    private String studentAddress;

//    @OneToMany(mappedBy = "markPk")
//    private List<Mark> markList;

}
