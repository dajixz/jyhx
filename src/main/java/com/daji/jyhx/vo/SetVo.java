package com.daji.jyhx.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 大稽
 * @date2018/11/2315:23
 */
@Data
public class SetVo {
    private String paperId;
//    private Map<String,Map<String,List<String>>> questions;
    private Map<String,List<String>> questions;
}
