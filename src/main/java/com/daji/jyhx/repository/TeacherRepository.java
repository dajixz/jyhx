package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 大稽
 * @date2018/10/1515:38
 */
public interface TeacherRepository  extends JpaRepository<Teacher,String>,JpaSpecificationExecutor<Teacher> {
    Teacher findTeacherByTeacherId(String teacherId);
    Page<Teacher> getTeachersByTeacherNameContaining(Pageable pageable ,String teacherName);

}
