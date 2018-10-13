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



    @GetMapping("/test")
    public String test(){
        String str = "1,2,3";
        String[] strAry = str.split(",");
        Integer[] intAry = new Integer[strAry.length];
        for(int i=0;i<strAry.length;i++){
            intAry[i]= Integer.parseInt(strAry[i].trim());
        }
        System.out.println(Arrays.toString(intAry));
        System.out.println(Arrays.asList(intAry));

        Integer[] i = {1,2,3};
        System.out.println(Arrays.toString(i).substring(1, Arrays.toString(i).length()-1));
        return "55";
    }

    @PostMapping("/getQuestions")
    public List getQuestionsByPaperId(String paperId){
        return paperService.getQuestionsByPaperId(paperId);
    }

    @PostMapping("/editPaper")
    public String editPaper(PaperVo paperVo){
        System.out.println(paperVo);
//        try {
            paperService.editPaper(paperVo);
//        }catch (Exception e){
//            return "exception";
//        }
        return "";
    }
}
