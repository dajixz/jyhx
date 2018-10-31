package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Answer;
import com.daji.jyhx.entity.QuestionPk;
import com.daji.jyhx.entity.Subjectivequestion;
import com.daji.jyhx.repository.AnswerRepository;
import com.daji.jyhx.repository.SubjectivequestionRepository;
import com.daji.jyhx.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Answer> getAnswersByPaperIdAndQuestionId(String paperId, Integer questionId,Integer page) {
        Pageable pageable =  PageRequest.of(page-1, 1);
//        List<Answer> answers = answerRepository.findAnswersByPaperIdAndQuestionId(paperId, questionId,Integer.valueOf(1),Integer.valueOf(1));
        Page<Answer> answers = answerRepository.findAnswersByPaperIdAndQuestionId(paperId, questionId,pageable);
        return answers;
    }

    @Override
    public Map<String, String> getAnswerInfoByPaperIdAndQuestionId(String paperId, Integer questionId) {
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
}
