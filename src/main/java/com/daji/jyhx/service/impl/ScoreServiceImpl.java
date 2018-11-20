package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.QuestionPk;
import com.daji.jyhx.entity.Score;
import com.daji.jyhx.entity.ScorePk;
import com.daji.jyhx.entity.Subjectivequestion;
import com.daji.jyhx.repository.ScoreRepository;
import com.daji.jyhx.repository.SubjectivequestionRepository;
import com.daji.jyhx.service.ScoreService;
import com.daji.jyhx.vo.ScoreVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 大稽
 * @date2018/11/115:08
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private SubjectivequestionRepository subjectivequestionRepository;

    @Override
    public Page<Score> getScoresByPaperIdAndQuestionId(String paperId, Integer questionId, Integer page) {
        Pageable pageable =  PageRequest.of(page-1, 1);
//        List<Answer> answers = answerRepository.findAnswersByPaperIdAndQuestionId(paperId, questionId,Integer.valueOf(1),Integer.valueOf(1));
        Page<Score> scores = scoreRepository.findScoresByPaperIdAndQuestionId(paperId, questionId,pageable);
        return scores;
    }

    @Override
    public Map<String, String> getScoreInfoByPaperIdAndQuestionId(String paperId, Integer questionId) {
        Map<String,String> infoMap = new HashMap<>();
        QuestionPk questionPk = new QuestionPk();
        questionPk.setQuestionPaperId(paperId);
        questionPk.setQuestionId(questionId);
        Subjectivequestion subjectivequestion = subjectivequestionRepository.findByQuestionPk(questionPk);
        String questionName = subjectivequestion.getQuestionName();
        String subjectiveQuestionContent = subjectivequestion.getSubjectiveQuestionContent();
        Double questionScore = subjectivequestion.getQuestionScore();
        infoMap.put("questionScore",Double.toString(questionScore));
        infoMap.put("questionName",questionName);
        infoMap.put("subjectiveQuestionContent",subjectiveQuestionContent);
        return infoMap;
    }

    @Override
    public Integer updateScoreByScorePk(ScoreVo scoreVo) {
        return scoreRepository.updateScoreByScorePk(scoreVo.getScorePaperId(),scoreVo.getScoreQuestionId(),scoreVo.getScoreStudentId(),scoreVo.getGoalScore(),scoreVo.getQuestionScore());
    }
}
