package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 大稽
 * @date2018/8/917:54
 */
@Embeddable
@Data
public class ScorePk implements Serializable {

//    //得分所属试卷id 复合主键 外键
//    @OneToOne
//    @JoinColumn(name = "score_paper_id")
//    private Paper paper;
//
//    //得分对应题目id 复合主键 外键
//    @OneToOne
//    @JoinColumn(name = "score_question_id",referencedColumnName = "question_id")
//    private Question question;
//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name="score_paper_id", referencedColumnName="question_paper_id"),
//            @JoinColumn(name="score_question_id", referencedColumnName="question_id")
//    })
//    private Question question;
    @Column(length = 32)
    private String scorePaperId;

    @Column(length = 32)
    private Integer scoreQuestionId;

    //得分对应学生id  复合主键 外键 一对一 一个得分对应一个学生
//    @OneToOne
//    @JoinColumn(name = "score_student_id")
//    private Student student;
    @Column(length = 32)
    private String scoreStudentId;


}
