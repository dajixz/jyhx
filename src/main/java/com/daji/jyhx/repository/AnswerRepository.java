package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Answer;
import com.daji.jyhx.entity.AnswerPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author 大稽
 * @date2018/10/1917:53
 */
public interface AnswerRepository extends JpaRepository<Answer,AnswerPk>{
    @Query(value="select * from answer as a where a.answer_paper_id=:paperId and a.answer_question_id=:questionId ",countQuery = "select count(*) from answer as a where a.answer_paper_id=:paperId and a.answer_question_id=:questionId",nativeQuery = true)
    Page<Answer> findAnswersByPaperIdAndQuestionId(@Param("paperId")String paperId,@Param("questionId")Integer questionId, Pageable pageable);
//    @Query(value = "select * from answer as a where a.answer_paper_id=?1 and a.answer_question_id=?2 limit ?3,?4",nativeQuery = true)
//    List<Answer> findAnswersByPaperIdAndQuestionId(String paperId, Integer questionId,Integer page,Integer pageSize);

}
