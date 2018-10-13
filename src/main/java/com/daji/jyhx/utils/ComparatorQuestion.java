package com.daji.jyhx.utils;

import com.daji.jyhx.entity.Objectivequestion;
import com.daji.jyhx.entity.Subjectivequestion;

import java.util.Comparator;

/**
 * @author 大稽
 * @date2018/9/1715:01
 */
public class ComparatorQuestion implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Objectivequestion oq1;
        Objectivequestion oq2;
        Subjectivequestion sq1;
        Subjectivequestion sq2;
        if(o1 instanceof Objectivequestion){
            oq1=(Objectivequestion)o1;
            if(o2 instanceof Subjectivequestion){
                sq1=(Subjectivequestion)o2;
                return oq1.getQuestionPk().getQuestionId().compareTo(sq1.getQuestionPk().getQuestionId());
            }else{
                oq2=(Objectivequestion)o2;
                return oq1.getQuestionPk().getQuestionId().compareTo(oq2.getQuestionPk().getQuestionId());
            }
        }else {
            sq2=(Subjectivequestion)o1;
            if(o2 instanceof Subjectivequestion){
                sq1=(Subjectivequestion)o2;
                return sq2.getQuestionPk().getQuestionId().compareTo(sq1.getQuestionPk().getQuestionId());
            }else {
                oq1=(Objectivequestion)o2;
                return sq2.getQuestionPk().getQuestionId().compareTo(oq1.getQuestionPk().getQuestionId());
            }
        }
    }
}
