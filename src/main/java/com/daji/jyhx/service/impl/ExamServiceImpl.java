package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Exam;
import com.daji.jyhx.repository.ExamRepository;
import com.daji.jyhx.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * @author 大稽
 * @date2018/8/1322:57
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public void addExam(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public Page<Exam> getExamList(Integer page) {
        Pageable pageable =  PageRequest.of(page-1, 5, Sort.Direction.ASC, "examTime");
        return examRepository.findAll(pageable);
    }
}
