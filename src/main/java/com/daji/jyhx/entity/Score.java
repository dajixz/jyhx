package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author 大稽
 * @date2018/8/918:08
 */
//得分表
@Entity
@Data
public class Score {

    //得分id
    @EmbeddedId
    private ScorePk scorePk;

    //得分对应题目分值
    private Double scoreQuestionScore;

    //得分
    private Double scoreGoalScore;
}
