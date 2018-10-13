package com.daji.jyhx.service;

import com.daji.jyhx.entity.Exam;
import org.springframework.data.domain.Page;

/**
 * @author 大稽
 * @date2018/8/1322:55
 */
public interface ExamService {

    void addExam(Exam exam);
    Page<Exam> getExamList(Integer page);
}
