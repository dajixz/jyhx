package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author 大稽
 * @date2018/10/1917:10
 */
@Embeddable
@Data
public class AnswerPk  implements Serializable {
    @Column(length = 32)
    private String answerStudentId;
    @Column(length = 32)
    private String answerPaperId;
    @Column(length = 32)
    private String answerQuestionId;
}
