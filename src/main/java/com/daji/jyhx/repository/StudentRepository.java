package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author 大稽
 * @date2018/8/1016:29
 */
public interface StudentRepository extends JpaRepository<Student,String>,JpaSpecificationExecutor<Student> {

}
