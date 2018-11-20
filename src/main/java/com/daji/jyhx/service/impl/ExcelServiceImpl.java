package com.daji.jyhx.service.impl;

import com.daji.jyhx.entity.Teacher;
import com.daji.jyhx.service.ExcelService;
import com.daji.jyhx.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 大稽
 * @date2018/11/2013:06
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public List<Teacher> importTeacherData(MultipartFile file) {
        Workbook wb ;
        List<Teacher> teacherList = new ArrayList<>();
        try {
            if (ExcelUtil.isExcel2007(file.getOriginalFilename())) {
                wb = new XSSFWorkbook(file.getInputStream());
            } else {
                wb = new HSSFWorkbook(file.getInputStream());
            }
        }catch (IOException e){
            return null;
        }
        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++)
        {
            if(i==0)continue;
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            Teacher teacher = new Teacher();
            for(Cell cell : row){
                cell.setCellType(CellType.STRING);
            }
            String id = row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            String name= row.getCell(1).getStringCellValue();//获取第i行的索引为0的单元格数据
            String tel= row.getCell(2).getStringCellValue();//获取第i行的索引为0的单元格数据
            String email= row.getCell(3).getStringCellValue();//获取第i行的索引为0的单元格数据
            teacher.setTeacherId(id);
            teacher.setTeacherName(name);
            teacher.setTeacherTel(tel);
            teacher.setTeacherEmail(email);
            teacherList.add(teacher);

        }
        return teacherList;
    }
}
