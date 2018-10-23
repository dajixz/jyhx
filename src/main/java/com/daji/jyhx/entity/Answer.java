package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * @author 大稽
 * @date2018/10/1917:17
 */
//答题表
@Entity
@Data
public class Answer {

    @EmbeddedId
    private AnswerPk answerPk;

    //TODO
    //答题内容（可为图片路径）
    private String answerContent;

}
