package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Exam;

import com.daji.jyhx.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

/**
 * @author 大稽
 * @date2018/8/1322:55
 */
@RestController("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/addExam")
    public void addExam(Exam exam){
        exam.setExamId(UUID.randomUUID().toString().replace("-", ""));
        examService.addExam(exam);
    }

    @GetMapping("/getExamList")
    public Page<Exam> getExamList(Integer page){
        return examService.getExamList(page);
    }
}
