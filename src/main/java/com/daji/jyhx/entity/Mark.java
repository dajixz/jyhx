package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author 大稽
 * @date2018/8/918:19
 */
//成绩表
@Entity
@Data
public class Mark {

    //成绩id
    @EmbeddedId
    private MarkPk markPk;

    //成绩所属科目
    @Column(length = 3)
    private String markSubject;

    //成绩分数
    @Column(length = 4)
    private Double markScore;

}
