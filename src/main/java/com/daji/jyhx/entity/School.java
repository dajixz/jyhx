package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/915:15
 */
//学校表
@Entity
@Data
public class School {

    // 学校id 主键
    @Id
    @Column(length = 32)
    private String schoolId;

    //学校名称
    private String schoolName;

    //一个学校有多个年级
    //级联保存、更新、删除、刷新;延迟加载。当删除学校，会级联删除该学校的所有年级
    //拥有mappedBy注解的实体类为关系被维护端
//    @OneToMany(mappedBy = "school",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
//    private List<Grade> gradeList;

}
