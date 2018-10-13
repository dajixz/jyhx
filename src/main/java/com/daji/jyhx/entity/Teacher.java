package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 大稽
 * @date2018/8/915:45
 */
//教师表
@Entity
@Data
public class Teacher {

    //教师id
    @Id
    private String teacherId;

    //教师姓名
    private String teacherName;

    //教师手机号
    private String teacherTel;

    //教师密码
    private String teacherPassword;

    //教师邮箱
    private String teacherEmail;
}
