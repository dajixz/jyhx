package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Answer;
import com.daji.jyhx.entity.AnswerPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 大稽
 * @date2018/10/1917:53
 */
public interface AnswerRepository extends JpaRepository<Answer,AnswerPk> {
    @Query(value = "select * from answer as a where a.answer_paper_id=:paperId and a.answer_question_id=:questionId",nativeQuery = true)
    List<Answer> getAnswersByPaperIdAndQuestionId(@Param("paperId")String paperId,@Param("questionId")Integer questionId);
}
