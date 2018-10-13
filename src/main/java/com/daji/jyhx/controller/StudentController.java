package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Student;
import com.daji.jyhx.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author 大稽
 * @date2018/8/1016:30
 */
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getStudentList")
    public Page<Student> getStudentList(Integer page){
        Pageable pageable =  PageRequest.of(page-1, 5, Sort.Direction.ASC, "studentId");
        Page<Student> all = studentRepository.findAll(pageable);
        return all;
    }
}
