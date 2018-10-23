package com.daji.jyhx.service;

import com.daji.jyhx.entity.Paper;
import com.daji.jyhx.vo.PaperVo;

import java.util.List;

/**
 * @author 大稽
 * @date2018/8/2113:41
 */
public interface PaperService {
    List<Paper> getPaperListByExamId(String examId);
    void addPaper(Paper paper);
    void editPaper(PaperVo paperVo) ;
    List getQuestionsByPaperId(String paperId);

}
