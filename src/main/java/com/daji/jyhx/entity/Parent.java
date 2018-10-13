package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 大稽
 * @date2018/8/915:41
 */
//家长表
@Entity
@Data
public class Parent {

    //家长id 主键
    @Id
    @Column(length = 32)
    private String parentId;

    //家长姓名
    private String parentName;

    //家长手机号
    private String parentTel;

    //家长密码
    private String parentPassword;

    //家长学生id 一个家长对应一个学生= =！
//    @OneToOne
//    @JoinColumn(name = "parent_student_id")
//    private Student student;
    @Column(length = 32)
    private String parentStudentId;

    //家长邮箱
    private String parentEmail;
}
