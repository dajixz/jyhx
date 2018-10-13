package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 大稽
 * @date2018/8/917:18
 */
//题目表
@Entity
@Data
public class Question {

    // 题目id 主键
    @EmbeddedId
    private QuestionPk questionPk;

//    @Column(length = 32)
//    private Integer questionId;
//
//    @Column(length = 32)
//    private String questionPaperId;

    //题目名称
    private String questionName;

    //题目所属科目
    private String questionSubject;

    //题目分值
    private Double questionScore;

    //题目类型 主观题客观题 0为主观题 1为客观题
    private Integer questionType;

    //题目答案
    private String questionAnswer;

    //A选项
    @Transient
    private String objectivequestionAoption;

    //B
    @Transient
    private String objectivequestion_b_option;

    //C
    @Transient
    private String objectivequestion_c_option;

    //D
    @Transient
    private String objectivequestion_d_option;

    //主观题内容
    @Transient
    private String subjectiveQuestionContent;

}
