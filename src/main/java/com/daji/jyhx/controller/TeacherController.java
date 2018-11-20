package com.daji.jyhx.controller;

import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.service.TeacherService;
import com.daji.jyhx.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author 大稽
 * @date2018/11/1712:48
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeacherList")
    public Page<Teacher> getTeacherList(Integer page) {
        Page<Teacher> teacherList = teacherService.getTeacherList(page);
        return teacherList;
    }

    @GetMapping("/getTeachers")
    public Page<Teacher> getTeachers(Integer page, String teacherName) {
        Page<Teacher> teachers = teacherService.getTeachers(page, teacherName);
        return teachers;
    }


}
