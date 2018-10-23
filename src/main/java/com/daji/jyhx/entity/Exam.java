package com.daji.jyhx.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author 大稽
 * @date2018/8/916:06
 */
//考试表
@Entity
@Data
public class Exam {

    //考试id  主键
    @Id
    @Column(length = 32)
    private String examId;

    //考试名称
    private String examName;

    //考试时间
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date examTime;

    //考试总分
    private Integer examTotalScore;

    //考试最高总分
    private Double examTopScore;

    private String examGradeId;

}
