package com.daji.jyhx.service;

import com.daji.jyhx.entity.Answer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 大稽
 * @date2018/10/1917:54
 */
public interface AnswerService {
    Page<Answer> getAnswersByPaperIdAndQuestionId(String paperId, Integer questionId, Integer page);
    Map<String,String> getAnswerInfoByPaperIdAndQuestionId(String paperId, Integer questionId);
}
