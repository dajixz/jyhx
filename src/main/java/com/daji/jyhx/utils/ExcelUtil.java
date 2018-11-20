package com.daji.jyhx.utils;

/**
 * @author 大稽
 * @date2018/11/1812:32
 */
public class ExcelUtil {
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath){
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
