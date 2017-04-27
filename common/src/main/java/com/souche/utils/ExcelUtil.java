package com.souche.utils;

import java.io.*;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


/**
 * @author guishangquan
 * @Description excel 文件工具类
 * @Package com.souche.utils
 * @date 17/4/27
 **/
public class ExcelUtil {
    /**
     *   创建一个Excel文件
     *   String fileName = "/tmp/hello.xls";
     *   String sheetName = "用户信息";
     *   List<String> titleRow = new ArrayList<String>();
     *   titleRow.add("姓名");
     *   titleRow.add("年龄");
     *   List<List<String>> dataRows = new ArrayList<List<String>>();
     *   List<String> data1 =  new ArrayList<String>();
     *   data1.add("张三");
     *   data1.add("30");
     *   List<String> data2 =  new ArrayList<String>();
     *   data2.add("李四");
     *   data2.add("33");
     *   dataRows.add(data1);
     *   dataRows.add(data2);
     *   File file = ExcelUtils.createExcel(fileName, sheetName, titleRow, dataRows);
     *   if(file!=null){
     *       System.out.println(file.getName());
     *   }
     * @param fileName 文件名 如 hello.xls
     * @param sheetName 表格名称
     * @param titleRow 标题行
     * @param dataRows 数据行
     * @return File 文件
     */
    public static File createExcel(String fileName, String sheetName, List<String> titleRow, List<List<String>> dataRows){
        if(fileName==null){
            fileName = "default";
        }
        if(sheetName==null){
            sheetName = "default";
        }
        Workbook wb = new SXSSFWorkbook(1000);
        SXSSFSheet sheet=(SXSSFSheet)wb.createSheet(sheetName);
        if(titleRow!=null){
            org.apache.poi.ss.usermodel.Row row=sheet.createRow(0);
            for(int i=0;i<titleRow.size();i++){
                org.apache.poi.ss.usermodel.Cell cell=row.createCell(i);
                cell.setCellValue(titleRow.get(i));
            }
        }
        if(dataRows!=null){
            for(int i=0;i<dataRows.size();i++){
                org.apache.poi.ss.usermodel.Row row=sheet.createRow(1+i);
                List<String> data = dataRows.get(i);
                if(data==null || data.size()==0){
                    continue;
                }
                for(int j=0;j<data.size();j++){
                    org.apache.poi.ss.usermodel.Cell cell=row.createCell(j);
                    cell.setCellValue(data.get(j));
                }
            }
        }
        FileOutputStream fileOS = null;
        File file = null;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOS = new FileOutputStream(file);
            wb.write(fileOS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                wb.close();
                fileOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     *   将excel内容输入到一个output流
     *   String sheetName = "用户信息";
     *   List<String> titleRow = new ArrayList<String>();
     *   titleRow.add("姓名");
     *   titleRow.add("年龄");
     *   List<List<String>> dataRows = new ArrayList<List<String>>();
     *   List<String> data1 =  new ArrayList<String>();
     *   data1.add("张三");
     *   data1.add("30");
     *   List<String> data2 =  new ArrayList<String>();
     *   data2.add("李四");
     *   data2.add("33");
     *   dataRows.add(data1);
     *   dataRows.add(data2);
     *   File file = ExcelUtils.createExcel(fileName, sheetName, titleRow, dataRows);
     *   if(file!=null){
     *       System.out.println(file.getName());
     *   }
     * @param out 需要输入的流
     * @param sheetName 表格名称
     * @param titleRow 标题行
     * @param dataRows 数据行
     * @return
     */
    public static void createExcelStream(OutputStream out, String sheetName, List<String> titleRow, List<List<String>> dataRows){
        if(sheetName==null){
            sheetName = "default";
        }
        Workbook wb = new SXSSFWorkbook(1000);
        SXSSFSheet sheet=(SXSSFSheet)wb.createSheet(sheetName);
        if(titleRow!=null){
            Row row=sheet.createRow(0);
            for(int i=0;i<titleRow.size();i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(titleRow.get(i));
            }
        }
        if(dataRows!=null){
            for(int i=0;i<dataRows.size();i++){
                Row row=sheet.createRow(1+i);
                List<String> data = dataRows.get(i);
                if(data==null || data.size()==0){
                    continue;
                }
                for(int j=0;j<data.size();j++){
                    Cell cell=row.createCell(j);
                    cell.setCellValue(data.get(j));
                }
            }
        }
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建一个工作台
     * 无限长度
     * @return
     */
    public static Workbook createWb(){
        return createWb(null);
    }

    /**
     * 创建一个工作台
     * @param size 如果size == null 表示无限长度
     * @return
     */
    public static Workbook createWb(Integer size){
        if(size == null){
            size = -1;
        }
        Workbook wb = new SXSSFWorkbook(size);
        return wb;
    }

    /**
     * 创建一个表单
     * @param wb
     * @param sheetName
     * @return
     */
    public static SXSSFSheet createSheet(Workbook wb, String sheetName, List<String> titleRow){
        if(sheetName==null){
            sheetName = "default";
        }
        SXSSFSheet sheet=(SXSSFSheet)wb.createSheet(sheetName);
        if(titleRow!=null){
            Row row=sheet.createRow(0);
            for(int i=0;i<titleRow.size();i++){
                Cell cell=row.createCell(i);
                cell.setCellValue(titleRow.get(i));
            }
        }
        return sheet;
    }

    /**
     *   将excel内容输入到一个output流
     *   String sheetName = "用户信息";
     *   List<String> titleRow = new ArrayList<String>();
     *   titleRow.add("姓名");
     *   titleRow.add("年龄");
     *   List<List<String>> dataRows = new ArrayList<List<String>>();
     *   List<String> data1 =  new ArrayList<String>();
     *   data1.add("张三");
     *   data1.add("30");
     *   List<String> data2 =  new ArrayList<String>();
     *   data2.add("李四");
     *   data2.add("33");
     *   dataRows.add(data1);
     *   dataRows.add(data2);
     *   File file = ExcelUtils.createExcel(fileName, sheetName, titleRow, dataRows);
     *   if(file!=null){
     *       System.out.println(file.getName());
     *   }
     * @param out 需要输入的流
     * @param sheetName 表格名称
     * @param titleRow 标题行
     * @param dataRows 数据行
     * @return
     */
    public static void continueWrite(SXSSFSheet sheet, List<List<String>> dataRows, int lastRow){
        if(dataRows!=null){
            for(int i=0;i<dataRows.size();i++){
                Row row=sheet.createRow(1+lastRow+i);
                List<String> data = dataRows.get(i);
                if(data==null || data.size()==0){
                    continue;
                }
                for(int j=0;j<data.size();j++){
                    Cell cell=row.createCell(j);
                    cell.setCellValue(data.get(j));
                }
            }
        }
    }

    /**
     * 关闭工作台
     * @param wb
     */
    public static void finish(OutputStream out, Workbook wb){
        try {
            wb.write(out);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
