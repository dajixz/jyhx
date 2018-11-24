package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 大稽
 * @date2018/10/1515:38
 */
public interface TeacherRepository  extends JpaRepository<Teacher,String>,JpaSpecificationExecutor<Teacher> {
    Teacher findTeacherByTeacherId(String teacherId);
    Page<Teacher> getTeachersByTeacherNameContaining(Pageable pageable ,String teacherName);

    @Query(value = "select t.* from teacher as t left join teacher_role as r on t.teacher_id = r.teacher_id where t.teacher_grade_id=:teacherGradeId and r.role_id=:roleId ",nativeQuery = true)
    List<Teacher> findTeachersByTeacherGradeIdAndRoleId(@Param("teacherGradeId")String teacherGradeId,@Param("roleId")Integer roleId);

    @Modifying
    @Query(value = "update Teacher set teacherResources = :resources where teacherId=:teacherId")
    void setTeacherToCorrect(@Param("resources")String resources,@Param("teacherId")String teacherId);

}
