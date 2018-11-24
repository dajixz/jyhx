package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.QuestionPk;
import com.daji.jyhx.entity.Score;

import com.daji.jyhx.entity.ScorePk;
import com.daji.jyhx.entity.Subjectivequestion;
import com.daji.jyhx.repository.ScoreRepository;
import com.daji.jyhx.repository.SubjectivequestionRepository;
import com.daji.jyhx.service.ScoreService;
import com.daji.jyhx.vo.ScoreVo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    @Transactional
    public Score getScoreByPaperIdAndQuestionIds(String paperId, String questionIds){
        List<String> questionIdList = Arrays.asList( questionIds.split(","));
//        List<Answer> answers = answerRepository.findAnswersByPaperIdAndQuestionId(paperId, questionId,Integer.valueOf(1),Integer.valueOf(1));
        Score score = scoreRepository.findScoreByPaperIdAndQuestionIds(paperId, questionIdList);
        if(score!=null){
            ScorePk scorePk = score.getScorePk();
            Integer scoreQuestionId = scorePk.getScoreQuestionId();
            Map<String, String> scoreInfoByPaperIdAndQuestionId = getScoreInfoByPaperIdAndQuestionId(paperId, scoreQuestionId);
            score.setScoreInfo(scoreInfoByPaperIdAndQuestionId);
        }
        return score;
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

    @Override
    public List<Integer> getSubjectiveQuestionIdByPaperId(String paperId) {
        List<Integer> subjectiveQuestionIdList = subjectivequestionRepository.findSubjectiveQuestionIdByPaperId(paperId);
        return subjectiveQuestionIdList;
    }
}
