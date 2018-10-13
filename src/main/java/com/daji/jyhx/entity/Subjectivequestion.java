package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/917:50
 */
//主观题表
@Entity
@Data
@Table(name = "subjective_question")
public class Subjectivequestion {

//    主观题id
//    @Id
//    private Integer questionId;
//
//    @Column(name="question_paper_id",length = 32)
//    private String questionPaperId;
//
    @EmbeddedId
    private QuestionPk questionPk;

    //题目类型 主观题客观题 0为主观题 1为客观题
    private Integer questionType;

    //题目分值
    private Double questionScore;

    //题目答案
    private String questionAnswer;

    //主观题名
    private String questionName;

    //主观题科目
    private String questionSubject;

    //主观题内容
    private String subjectiveQuestionContent;

    @Transient
    private Integer subjectiveNum;

    @Transient
    private List<Subjectivequestion> subjectivequestionList;


}
