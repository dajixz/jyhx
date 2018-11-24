package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Objectivequestion;
import com.daji.jyhx.entity.Paper;
import com.daji.jyhx.service.PaperService;
import com.daji.jyhx.vo.PaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author 大稽
 * @date2018/8/2020:13
 */
@RestController()
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping("/addPaper")
    public void addPaper(Paper paper){
        paper.setPaperId(UUID.randomUUID().toString().replace("-", ""));
        paperService.addPaper(paper);
    }
    @GetMapping("/getPaperList")
    public List<Paper> getPaperListByExamId(String examId){
        List<Paper> paperList = paperService.getPaperListByExamId(examId);
        return paperList;
    }

    @PostMapping("/getQuestions")
    public List getQuestionsByPaperId(String paperId){
        return paperService.getQuestionsByPaperId(paperId);
    }

    @PostMapping("/editPaper")
    public String editPaper(PaperVo paperVo){
//        try {
            paperService.editPaper(paperVo);
//        }catch (Exception e){
//            return "exception";
//        }
        return "";
    }

}
