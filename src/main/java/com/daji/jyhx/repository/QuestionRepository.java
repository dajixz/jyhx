package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Question;
import com.daji.jyhx.entity.QuestionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 大稽
 * @date2018/9/812:25
 */
public interface QuestionRepository extends JpaRepository<Question,QuestionPk> {
    @Query(value = "select * from question as q , objective_question as oq where q.question_id=oq.objective_question_question_id and q.question_paper_id=:paperId",nativeQuery = true)
    List<Question> getObjectiveQuestionByPaperId(@Param("paperId") String paperId);

    @Query(value = "select * from question as q ,subjective_question as sq where q.question_id=sq.subjective_question_question_id and q.question_paper_id=:paperId",nativeQuery = true)
    List<Question> getSbjectiveQuestionByPaperId(@Param("paperId") String paperId);
}
