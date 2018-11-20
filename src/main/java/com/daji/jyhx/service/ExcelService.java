package com.daji.jyhx.service;

import com.daji.jyhx.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 大稽
 * @date2018/11/2013:04
 */
public interface ExcelService {

    List<Teacher> importTeacherData(MultipartFile file);
}
