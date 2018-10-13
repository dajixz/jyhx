package com.daji.jyhx.repository;

import com.daji.jyhx.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 大稽
 * @date2018/8/2113:40
 */
public interface PaperRespository extends JpaRepository<Paper,String> {
    List<Paper> findPapersByPaperExamId(String examId);

    @Modifying
    @Query(value = "update Paper set paperSubNum = :subNum where paperId=:paperId")
    void updateSubNum(@Param("subNum")String subNum,@Param("paperId")String paperId);

    @Query(value = "select paperSubNum from Paper where paperId =:paperId")
    String getPaperSubNum(@Param("paperId")String paperId);
}
