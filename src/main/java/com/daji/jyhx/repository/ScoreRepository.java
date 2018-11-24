package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Score;
import com.daji.jyhx.entity.ScorePk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/114:57
 */
public interface ScoreRepository extends JpaRepository<Score,ScorePk> {

//    @Query(value = "select * from score as s where s.score_paper_id=:paperId and s.score_question_id=:questionId and s.state is null",countQuery = "select count(*) from score as s where s.score_paper_id=:paperId and s.score_question_id=:questionId and s.state is null",nativeQuery = true)
//    Page<Score> findScoresByPaperIdAndQuestionId(@Param("paperId")String paperId, @Param("questionId")Integer questionId, Pageable pageable);
    @Query(value = "select * from score as s where s.score_paper_id=:paperId and s.score_question_id in (:questionIdList) and s.state is null limit 1",nativeQuery = true)
    Score findScoreByPaperIdAndQuestionIds(@Param("paperId")String paperId,@Param("questionIdList")List<String> questionIdList);

    @Transactional
    @Modifying
    @Query(value = "update score as s set s.score_goal_score =:goalScore ,s.score_question_score=:questionScore,s.state =1 where s.score_paper_id=:paperId and s.score_question_id=:questionId and s.score_student_id=:studentId",nativeQuery = true)
    Integer updateScoreByScorePk(@Param("paperId")String paperId, @Param("questionId")Integer questionId, @Param("studentId")String studentId, @Param("goalScore")Double goalScore, @Param("questionScore")Double questionScore);
}
