package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Score;
import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.service.RoleService;
import com.daji.jyhx.service.ScoreService;
import com.daji.jyhx.service.TeacherService;
import com.daji.jyhx.vo.ResponseVo;
import com.daji.jyhx.vo.ScoreVo;
import com.daji.jyhx.vo.SetVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getScores")
    public ResponseVo getScoreByPaperIdAndQuestionId(String paperId, Authentication authentication) {

        ResponseVo responseVo = new ResponseVo();
        Teacher principal = (Teacher) authentication.getPrincipal();
        String teacherResources = principal.getTeacherResources();


        if(StringUtils.isNotBlank(teacherResources)){
            String[] split = teacherResources.split(":");
            if(!split[0].equals(paperId)){
                responseVo.setCode(403);
                responseVo.setMsg("你没有相应的批改权限！");
                return responseVo;
            }
            String questionIds = split[1].substring(0,split[1].length()-1);
            Score score = scoreService.getScoreByPaperIdAndQuestionIds(paperId, questionIds);
            if(score!=null){
                responseVo.setCode(200);
                responseVo.setData(score);
                return  responseVo;
            }else {
                responseVo.setCode(201);
                responseVo.setMsg("没有需要批改的了~ 2秒后自动退出蛤~");
                return  responseVo;
            }

        }
        responseVo.setCode(403);
        responseVo.setMsg("你没有相应的批改权限！");
        return  responseVo;
    }

    @PutMapping("/updateScore")
    public ResponseVo updateScore(ScoreVo scoreVo) {
        ResponseVo responseVo = new ResponseVo();
        Integer integer = scoreService.updateScoreByScorePk(scoreVo);
        responseVo.setCode(integer);
        return responseVo;
    }

    @GetMapping("/getTeachersToCorrect")
    public ResponseVo getTeachersToCorrect(String subject, Authentication authentication) {
        ResponseVo responseVo = new ResponseVo();
        Teacher principal = (Teacher) authentication.getPrincipal();
        String teacherGradeId = principal.getTeacherGradeId();
        List<Teacher> teacherList = teacherService.findTeachersByTeacherGradeIdAndSubject(teacherGradeId, subject);
        if (teacherList != null) {
            responseVo.setCode(200);
            responseVo.setData(teacherList);
            return responseVo;
        }
        responseVo.setCode(403);
        return responseVo;
    }

    @GetMapping("/getSubjectiveQuestionId")
    public ResponseVo getSubjectiveQuestionId(String paperId) {
        ResponseVo responseVo = new ResponseVo();
        List<Integer> subjectiveQuestionIdList = scoreService.getSubjectiveQuestionIdByPaperId(paperId);
        if (subjectiveQuestionIdList != null) {
            responseVo.setCode(200);
            responseVo.setData(subjectiveQuestionIdList);
            return responseVo;
        }
        responseVo.setCode(403);
        return responseVo;
    }

    @PutMapping("/setTeachersToCorrect")
    public ResponseVo setTeachersToCorrect(SetVo setVo){
        ResponseVo responseVo = new ResponseVo();
        System.out.println(setVo);
        teacherService.setTeachersToCorrect(setVo);
        responseVo.setCode(200);
        return responseVo;
    }
}
