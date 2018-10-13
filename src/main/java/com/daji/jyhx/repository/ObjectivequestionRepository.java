package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Objectivequestion;
import com.daji.jyhx.entity.Question;
import com.daji.jyhx.vo.QuestionVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 大稽
 * @date2018/9/812:26
 */
public interface ObjectivequestionRepository extends JpaRepository<Objectivequestion,String> {

    @Query(value = "select * from objective_question as oq where oq.question_paper_id=:paperId",nativeQuery = true)
    List<Objectivequestion> getObjectiveQuestionByPaperId(@Param("paperId") String paperId);
}
