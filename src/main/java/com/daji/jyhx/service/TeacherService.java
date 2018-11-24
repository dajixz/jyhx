package com.daji.jyhx.service;


import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.vo.SetVo;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/1712:51
 */
public interface TeacherService {
    Page<Teacher> getTeacherList(Integer page);

    Page<Teacher> getTeachers(Integer page,String teacherName);

    Teacher addTeacher(Teacher teacher);

    Teacher getTeacherByTeacherId(String teacherId);

    Teacher updateTeacher(Teacher teacher);

    List<Teacher> saveBathTeachers(List<Teacher> teachers,String teacherSchoolId);

    List<Teacher> findTeachersByTeacherGradeIdAndSubject(String teacherGradeId, String subject);

    void setTeachersToCorrect(SetVo setVo);
}
