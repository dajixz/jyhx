package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 大稽
 * @date2018/8/917:43
 */
//客观题表
@Entity
@Data
@Table(name = "objective_question")
public class Objectivequestion {

    //客观题id 主键
//    @Id
//    private Integer questionId;
//
//    @Column(name="question_paper_id",length = 32)
//    private String questionPaperId;
    @EmbeddedId
    private QuestionPk questionPk;
    //对应题目id
//    @OneToOne
//    @JoinColumn(name = "objective_question_question_id")
//    private Paper paper;
//    private Integer objectiveQuestionQuestionId;
    //题目类型 主观题客观题 0为主观题 1为客观题
    private Integer questionType;

    //题目分值
    private Double questionScore;

    //题目答案
    private String questionAnswer;

    //客观题名
    private String questionName;

    //客观题科目
    private String questionSubject;

    //A选项
    @Column(name = "objective_question_a_option")
    private String objectivequestion_A_option;

    //B
    @Column(name = "objective_question_b_option")
    private String objectivequestion_B_option;

    //C
    @Column(name = "objective_question_c_option")
    private String objectivequestion_C_option;

    //D
    @Column(name = "objective_question_d_option")
    private String objectivequestion_D_option;

}

