package com.daji.jyhx.vo;


/**
 * @author 大稽
 * @date2018/9/1117:03
 */
public class QuestionVo {

    private Integer questionId;

    private String questionPaperId;

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

    private String objectivequestionAoption;

    //B

    private String objectivequestion_b_option;

    //C
    private String objectivequestion_c_option;

    //D

    private String objectivequestion_d_option;

    //主观题内容
    private String subjectiveQuestionContent;
}
