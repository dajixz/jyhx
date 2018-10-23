package com.daji.jyhx.repository;


import com.daji.jyhx.entity.QuestionPk;
import com.daji.jyhx.entity.Subjectivequestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author 大稽
 * @date2018/9/812:27
 */
public interface SubjectivequestionRepository extends JpaRepository<Subjectivequestion,QuestionPk> {

    @Query(value = "select * from subjective_question as sq where sq.question_paper_id=:paperId",nativeQuery = true)
    List<Subjectivequestion> getSubjectiveQuestionByPaperId(@Param("paperId") String paperId);

    @Query(value = "select count(*) from subjective_question as sq where sq.question_paper_id=:paperId group by sq.subjective_question_content",nativeQuery = true)
    List<BigInteger> getSubjectiveNumByPaperId(@Param("paperId")String paperId);

    Subjectivequestion findByQuestionPk(QuestionPk questionPk);
}
