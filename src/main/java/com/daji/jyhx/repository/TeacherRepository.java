package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 大稽
 * @date2018/10/1515:38
 */
public interface TeacherRepository  extends JpaRepository<Teacher,String>{

    Teacher findTeacherByTeacherId(String teacherId);
}
