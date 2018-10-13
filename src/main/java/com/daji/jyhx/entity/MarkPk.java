package com.daji.jyhx.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author 大稽
 * @date2018/8/918:13
 */
@Embeddable
public class MarkPk implements Serializable{

    //成绩所属学生id 复合主键 外键 多对一 一个学生对应多个成绩
//    @OneToOne
//    @JoinColumn(name = "mark_student_id")
//    private Student student;
    @Column(length = 32)
    private String markStudentId;

    // 成绩对应考试id 复合主键 外键
//    @OneToOne
//    @JoinColumn(name = "mark_exam_id")
//    private Exam exam;
    @Column(length = 32)
    private String markExamId;

    //成绩对应试卷id 复合主键 外键
//    @OneToOne
//    @JoinColumn(name = "mark_paper_id")
//    private Paper paper;
    @Column(length = 32)
    private String markPaperId;
}
