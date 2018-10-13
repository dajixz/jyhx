package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/915:18
 */
//年级表
@Entity
@Data
public class Grade {

    //年级id 主键
    @Id
    private String gradeId;

    //年级名称(几届)
    private String gradeName;

    //年级所属学校id  外键 多对一    一个学校有多个年级
//    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    //可选属性optional=false,表示school不能为空。删除年级，不影响学校
//    @JoinColumn(name = "grade_school_id")
//    private School school;
    private String gradeSchoolId;

    //一个学校有多个年级
    //级联保存、更新、删除、刷新;延迟加载。当删除年级，会级联删除该年级的所有班级
    //拥有mappedBy注解的实体类为关系被维护端
//    @OneToMany(mappedBy = "grade",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
//    private List<Clazz> clazzList;

    //一个年级有多个学生
//    @OneToMany(mappedBy = "grade")
//    private List<Student> studentList;

}
