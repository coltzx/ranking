package sample;

import org.apache.poi.hssf.usermodel.*;
import pojo.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class DataToExcel {
    /**
     * 产生Excel试卷
     *@param list 题目集合
     */
    public static void exportExcelPaper(List<Model> list) {
        // 标题
        String[] title = { "序号", "队名", "模型质量", "砝码质量", "计算书分", "模型外观", "总分", "一级成功", "二级成功" };
        // 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 设置列宽
        setColumnWidth(sheet, 6);
        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 创建一个单元格
        HSSFCell cell = null;
        // 创建表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            // 设置样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            //cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置字体居中
            // 设置字体
            HSSFFont font = workbook.createFont();
            font.setFontName("宋体");
            //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
            // font.setFontHeight((short)12);
            font.setFontHeightInPoints((short) 13);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);

            cell.setCellValue(title[i]);
        }

        System.out.println(list);
        // 从第二行开始追加数据
        for (int i = 1; i < (list.size() + 1); i++) {
            // 创建第i行
            HSSFRow nextRow = sheet.createRow(i);
            for (int j = 0; j < 9; j++) {
                Model model = list.get(i-1);
                HSSFCell cell2 = nextRow.createCell(j);
                if (j == 0) {
                    cell2.setCellValue( model.getId());
                }
                if (j == 1) {
                    cell2.setCellValue(model.getName());
                }
                if (j == 2) {
                    cell2.setCellValue(model.getA());
                }
                if (j == 3) {
                    cell2.setCellValue(model.getB());
                }
                if (j == 4) {
                    cell2.setCellValue(model.getG());
                }
                if (j == 5) {
                    cell2.setCellValue(model.getH());
                }
                if (j == 6) {
                    cell2.setCellValue(model.getI());
                }
                if (j == 7) {
                    cell2.setCellValue(model.getC());
                }
                if (j == 8){
                    cell2.setCellValue(model.getD());
                }
            }
        }

        // 创建一个文件
        File file = new File("D:/ranking.xls");
        try {
            file.createNewFile();
            // 打开文件流
            FileOutputStream outputStream = FileUtils.openOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // 设置列宽()
    private static void setColumnWidth(HSSFSheet sheet, int colNum) {
        for (int i = 0; i < colNum; i++) {
            int v = 0;
            v = Math.round(Float.parseFloat("15.0") * 37F);
            v = Math.round(Float.parseFloat("20.0") * 267.5F);
            sheet.setColumnWidth(i, v);
        }
    }

}