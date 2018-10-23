package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Answer;
import com.daji.jyhx.entity.QuestionPk;
import com.daji.jyhx.entity.Subjectivequestion;
import com.daji.jyhx.repository.AnswerRepository;
import com.daji.jyhx.repository.SubjectivequestionRepository;
import com.daji.jyhx.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 大稽
 * @date2018/10/2318:30
 */
@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SubjectivequestionRepository subjectivequestionRepository;

    @Override
    @Transactional
    public Map<String,Object> getAnswersByPaperIdAndQuestionId(String paperId, Integer questionId) {
        Map<String,Object> answerResult = new HashMap<>();
        List<Answer> answers = answerRepository.getAnswersByPaperIdAndQuestionId(paperId, questionId);
        QuestionPk questionPk = new QuestionPk();
        questionPk.setQuestionPaperId(paperId);
        questionPk.setQuestionId(questionId);
        Subjectivequestion subjectivequestion = subjectivequestionRepository.findByQuestionPk(questionPk);
        String questionName = subjectivequestion.getQuestionName();
        String subjectiveQuestionContent = subjectivequestion.getSubjectiveQuestionContent();
        answerResult.put("questionName",questionName);
        answerResult.put("subjectiveQuestionContent",subjectiveQuestionContent);
        answerResult.put("answers",answers);
        return answerResult;
    }
}
