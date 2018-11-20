package com.daji.jyhx.vo;


import lombok.Data;

/**
 * @author 大稽
 * @date2018/11/115:53
 */
@Data
public class ScoreVo {

    private String scorePaperId;

    private Integer scoreQuestionId;

    private String scoreStudentId;

    private Double questionScore;

    private Double goalScore;
}
