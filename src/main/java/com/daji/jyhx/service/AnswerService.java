package com.daji.jyhx.service;

import com.daji.jyhx.entity.Answer;

import java.util.List;
import java.util.Map;

/**
 * @author 大稽
 * @date2018/10/1917:54
 */
public interface AnswerService {
    Map<String,Object> getAnswersByPaperIdAndQuestionId(String paperId, Integer questionId);
}
