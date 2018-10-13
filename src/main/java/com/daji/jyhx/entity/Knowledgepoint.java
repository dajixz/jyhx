package com.daji.jyhx.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 大稽
 * @date2018/8/917:24
 */
//知识点表
@Entity
@Data
public class Knowledgepoint {

    // 知识点id
    @Id
    private String knowledgepointId;

    //知识点名
    private String knowledgepointName;

    //知识点所属科目
    private String knowledgepointSubejct;


}
