package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 大稽
 * @date2018/8/916:11
 */
//试卷表
@Entity
@Data
public class Paper {

    //试卷id
    @Id
    @Column(length = 32)
    private String paperId;

    //所属考试id  外键 多对一 一场考试有多门科目(试卷)
//    @ManyToOne
//    @JoinColumn(name = "paper_exam_id")
//    private Exam exam;
    private String paperExamId;

    //试卷名称
    private String paperName;

    //试卷出卷人
    private String paperMaker;

    //试卷所属科目
    private String paperSubject;

    //试卷总分
    private Integer paperTotalScore;

    //试卷主观题数目列表
    private String paperSubNum;

}
