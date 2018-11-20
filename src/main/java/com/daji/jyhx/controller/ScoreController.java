package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Score;
import com.daji.jyhx.service.ScoreService;
import com.daji.jyhx.vo.ScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 大稽
 * @date2018/11/115:10
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/getScores")
    public Page<Score> getScoresByPaperIdAndQuestionId(String paperId, Integer questionId, Integer page){
        return scoreService.getScoresByPaperIdAndQuestionId(paperId,questionId,page);
    }

    @GetMapping("/getScoreInfo")
    public Map<String,String> getScoreInfoByPaperIdAndQuestionId(String paperId, Integer questionId){
        return scoreService.getScoreInfoByPaperIdAndQuestionId(paperId,questionId);
    }

    @PutMapping("/updateScore")
    public String updateScore(ScoreVo scoreVo){
        System.out.println(scoreVo);
        Integer integer = scoreService.updateScoreByScorePk(scoreVo);
        System.out.println(integer);
        return "s";
    }
}
