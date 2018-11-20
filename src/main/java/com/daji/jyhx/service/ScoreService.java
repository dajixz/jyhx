package com.daji.jyhx.service;

import com.daji.jyhx.entity.Score;
import com.daji.jyhx.vo.ScoreVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author 大稽
 * @date2018/11/115:00
 */
public interface ScoreService {
    Page<Score> getScoresByPaperIdAndQuestionId(String paperId, Integer questionId, Integer page);
    Map<String, String> getScoreInfoByPaperIdAndQuestionId(String paperId, Integer questionId);
    Integer updateScoreByScorePk(ScoreVo scoreVo);
}
