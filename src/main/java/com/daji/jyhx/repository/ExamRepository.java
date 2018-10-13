package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 大稽
 * @date2018/8/1322:56
 */
public interface ExamRepository extends JpaRepository<Exam,String>,JpaSpecificationExecutor<Exam> {

}
