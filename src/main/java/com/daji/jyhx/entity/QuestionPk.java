package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author 大稽
 * @date2018/8/918:03
 */
@Embeddable
@Data
public class QuestionPk implements Serializable {

    //题目id 复合主键
    @Column(length = 32)
    private Integer questionId;

    //题目所属试卷id 复合主键 外键 多对一 一张试卷有多道题目
//    @ManyToOne
//    @JoinColumn(name = "question_paper_id")
//    private Paper paper;
    @Column(name="question_paper_id",length = 32)
    private String questionPaperId;
}
