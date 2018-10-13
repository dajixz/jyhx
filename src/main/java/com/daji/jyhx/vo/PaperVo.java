package com.daji.jyhx.vo;

import lombok.Data;

/**
 * @author 大稽
 * @date2018/9/615:05
 */
@Data
public class PaperVo {
    private String paperId;
    private String subject;
    private String[] a;
    private String[] b;
    private String[] c;
    private String[] d;
    private String[] objectiveName;
    private String[] subjectiveName;
    private Integer[] subjectiveNum;
    private Double[] sscore;
    private Double[] oscore;
    private String[] answer;
    private String[] content;
    private Integer[] sindex;
    private Integer[] oindex;
}
