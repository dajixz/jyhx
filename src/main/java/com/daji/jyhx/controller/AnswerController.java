package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Answer;
import com.daji.jyhx.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

/**
 * @author 大稽
 * @date2018/10/2318:27
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/getAnswers")
    public Map<String,Object> getAnswerByPaperIdAndQuestionId(String paperId, Integer questionId){
        return answerService.getAnswersByPaperIdAndQuestionId(paperId,questionId);
    }

}