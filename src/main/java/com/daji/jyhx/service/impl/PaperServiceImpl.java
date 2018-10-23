package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.*;
import com.daji.jyhx.repository.ObjectivequestionRepository;
import com.daji.jyhx.repository.PaperRespository;
import com.daji.jyhx.repository.SubjectivequestionRepository;
import com.daji.jyhx.service.PaperService;
import com.daji.jyhx.utils.ComparatorQuestion;
import com.daji.jyhx.vo.PaperVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * @author 大稽
 * @date2018/8/2113:43
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRespository paperRespository;

    @Autowired
    private SubjectivequestionRepository subjectivequestionRepository;

    @Autowired
    private ObjectivequestionRepository objectivequestionRepository;

    @Override
    public List<Paper> getPaperListByExamId(String examId) {
        List<Paper> papers = paperRespository.findPapersByPaperExamId(examId);
        return papers;
    }

    @Override
    public void addPaper(Paper paper) {
        paperRespository.save(paper);
    }

    @Override
    @Transactional
    public void editPaper(PaperVo paperVo)  {
        List<Objectivequestion> objectivequestionList = new ArrayList<>();
        List<Subjectivequestion> subjectivequestionList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        String subject = paperVo.getSubject();
        String paperId = paperVo.getPaperId();
        for (int i = 0; i < paperVo.getObjectiveName().length; i++) {
            Objectivequestion objectivequestion = new Objectivequestion();
            QuestionPk questionPk = new QuestionPk();
            questionPk.setQuestionId(paperVo.getOindex()[i]);
            questionPk.setQuestionPaperId(paperId);
            objectivequestion.setQuestionPk(questionPk);
//            objectivequestion.setQuestionId(paperVo.getOindex()[i]);
//            objectivequestion.setQuestionPaperId(paperId);
            objectivequestion.setQuestionName(paperVo.getObjectiveName()[i]);
            objectivequestion.setObjectivequestion_A_option(paperVo.getA()[i]);
            objectivequestion.setObjectivequestion_B_option(paperVo.getB()[i]);
            objectivequestion.setObjectivequestion_C_option(paperVo.getC()[i]);
            objectivequestion.setObjectivequestion_D_option(paperVo.getD()[i]);
            objectivequestion.setQuestionSubject(subject);
            objectivequestion.setQuestionAnswer(paperVo.getAnswer()[i]);
            objectivequestion.setQuestionScore(paperVo.getOscore()[i]);
            objectivequestion.setQuestionType(1);//0为主观题 1为客观题
            objectivequestionList.add(objectivequestion);
        }
        for (int i = 0; i < paperVo.getSubjectiveName().length; i++) {
            Subjectivequestion subjectivequestion = new Subjectivequestion();
            QuestionPk questionPk = new QuestionPk();
            questionPk.setQuestionId(paperVo.getSindex()[i]);
            questionPk.setQuestionPaperId(paperId);
            subjectivequestion.setQuestionPk(questionPk);
//            subjectivequestion.setQuestionId(paperVo.getSindex()[i]);
//            subjectivequestion.setQuestionPaperId(paperId);
            subjectivequestion.setQuestionName(paperVo.getSubjectiveName()[i]);
            subjectivequestion.setQuestionSubject(paperVo.getSubject());
            subjectivequestion.setQuestionType(0);//0为主观题 1为客观题
            subjectivequestion.setQuestionScore(paperVo.getSscore()[i]);
            subjectivequestionList.add(subjectivequestion);
        }
        for (int j = 0; j < paperVo.getSubjectiveNum().length; j++) {
            Integer temp = 0;
            if (j != 0) {
                for(int i=0;i<j;i++){
                    temp += paperVo.getSubjectiveNum()[i];
                }
            }
            for (int k = 0; k < paperVo.getSubjectiveNum()[j]; k++) {
                subjectivequestionList.get(k + temp).setSubjectiveQuestionContent(paperVo.getContent()[j]);
            }
        }
        Integer[] subjectiveNum = paperVo.getSubjectiveNum();
        String s = Arrays.toString(subjectiveNum);
        String subNum = (s.substring(1, s.length() - 1)).trim();
        paperRespository.updateSubNum(subNum, paperId);
        objectivequestionRepository.saveAll(objectivequestionList);
        subjectivequestionRepository.saveAll(subjectivequestionList);
    }

    @Override
    public List getQuestionsByPaperId(String paperId) {
        List<Objectivequestion> objectiveQuestions = objectivequestionRepository.getObjectiveQuestionByPaperId(paperId);
        List<Subjectivequestion> subjectiveQuestions = subjectivequestionRepository.getSubjectiveQuestionByPaperId(paperId);
        String str = paperRespository.getPaperSubNum(paperId);
        ComparatorQuestion comparatorQuestion = new ComparatorQuestion();
        if (StringUtils.isNotBlank(str)) {
            String[] strAry = str.split(",");
            Integer[] intAry = new Integer[strAry.length];
            for (int i = 0; i < strAry.length; i++) {
                intAry[i] = Integer.parseInt(strAry[i].trim());
            }
            List<Integer> list = Arrays.asList(intAry);
            List<Integer> subjectiveNum = new ArrayList<>(list);


            Collections.sort(subjectiveQuestions, comparatorQuestion);
            subjectiveNum.add(0, Integer.valueOf(0));//0 2 3

            for (int i = 1; i < subjectiveNum.size(); i++) {
                Integer intTemp = Integer.valueOf(subjectiveNum.get(i).toString()) - 1;//2 3   1 2

                for (int j = i - 1; j < subjectiveQuestions.size(); ) {
                    List<Subjectivequestion> subjectivequestionList = new ArrayList<>();
                    subjectiveQuestions.get(j).setSubjectiveNum(intTemp+1);
                    for (int k = 1; intTemp > 0; intTemp--) {
                        Subjectivequestion subjectivequestion = subjectiveQuestions.get(j + k);
                        subjectiveQuestions.remove(j + k);
                        subjectivequestionList.add(subjectivequestion);
                    }
                    subjectiveQuestions.get(j).setSubjectivequestionList(subjectivequestionList);
                    break;
                }
            }
        }
        List questionList = new ArrayList();
        questionList.addAll(objectiveQuestions);
        questionList.addAll(subjectiveQuestions);
        Collections.sort(questionList, comparatorQuestion);
        return questionList;
    }
}
