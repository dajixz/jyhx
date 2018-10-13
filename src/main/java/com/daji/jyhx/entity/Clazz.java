package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/915:24
 */
//班级表
@Entity
@Data
public class Clazz {

    //班级id 主键
    @Id
    private String clazzId;

    //班级名称(几届几班)
    private String clazzName;

    //班级所属年级id 外键 多对一 一个年级有多个班级
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
//    @JoinColumn(name = "clazz_grade_id")
//    private Grade grade;
    private String clazzGradeId;

    //一个班级有多个学生
//    @OneToMany(mappedBy = "clazz")
//    private List<Student> studentList;

}
