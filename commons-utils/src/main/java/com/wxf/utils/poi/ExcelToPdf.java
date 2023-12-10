package com.wxf.utils.poi;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelToPdf {

    // public static final String DEST = "pdf/tables.pdf";
    public static final String DEST = "tables.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        // File file = new File(DEST);
        // file.getParentFile().mkdirs();
        // new ExcelToPdf().dataToPdf(DEST);

        excelToPdf();
    }

    public static void excelToPdf() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        font.setSize(13);
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("tables.pdf")));
        document.open();
        HSSFWorkbook workbook = new HSSFWorkbook(Files.newInputStream(new File("001_01649995252973.xls").toPath()));
        HSSFSheet sheet = workbook.getSheetAt(0);
        int column = sheet.getRow(0).getLastCellNum();
        int row = sheet.getPhysicalNumberOfRows();
        PdfPTable table = new PdfPTable(column - sheet.getRow(0).getFirstCellNum());
        String str = null;
        for (int i = sheet.getFirstRowNum(); i < row; i++) {
            for (int j = sheet.getRow(0).getFirstCellNum(); j < column; j++) {
                // 得到excel单元格的内容
                HSSFCell cell = sheet.getRow(i).getCell((short) j);
                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    str = (int) cell.getNumericCellValue() + "";
                } else {
                    str = cell.getStringCellValue();
                }
                // 创建pdf单元格对象，并往pdf单元格对象赋值。
                PdfPCell cells = new PdfPCell(new Paragraph(str, font));
                // pdf单元格对象添加到table对象
                table.addCell(cells);
            }
        }
        document.add(table);
        document.close();
        writer.close();
    }

    /**
     * 数据转pdf
     */
    public void dataToPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(dest)));
        document.open();

        // 使用语言包字体
        BaseFont abf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        // 字体
        Font font = new Font(abf, 8);

        // 段落
        Paragraph p = new Paragraph("测试结算单", new Font(abf, 12, Font.BOLD));
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        // 表格
        PdfPTable table = new PdfPTable(8);// numcolumns:列数
        table.setSpacingBefore(16f);// 表格与上面段落的空隙

        // 表格列创建并赋值
        PdfPCell cell = new PdfPCell(new Phrase("单位名称：测试有限公司", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 居中
        cell.disableBorderSide(13);// 去除左右上边框，保留下边框
        cell.setColspan(4);// 合并列数
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("日期：2020-06-05", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.disableBorderSide(13);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("单位（元）", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.disableBorderSide(13);
        cell.setColspan(1);
        table.addCell(cell);
        // 首行
        cell = new PdfPCell(new Phrase("期间", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("月份", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("分类", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("年利率", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("日利率", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("基数", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("利息", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("起始日：2020-03-26\n" + "结束日：2020-04-25", font));
        cell.setPadding(16f);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRowspan(3);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("4", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("资金", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1.10%", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("0.000031", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("10598164.91", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("325.01", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("4", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("资金", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1.10%", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("0.000031", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("-", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("-", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("4", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("资金", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1.10%", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("0.000031", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("-", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("-", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("合计", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(7);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("325.01", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("会计制单:", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.disableBorderSide(14);
        cell.setColspan(4);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("复核:", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.disableBorderSide(14);
        cell.setColspan(4);
        table.addCell(cell);
        table.setSpacingBefore(16f);
        document.add(table);

        // 下一页
        document.newPage();
        // 段落
        Paragraph p1 = new Paragraph("下一页测试结算单", new Font(abf, 12, Font.BOLD));
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p1);

        // 表格
        table = new PdfPTable(8);// numcolumns:列数
        table.setSpacingBefore(16f);// 表格与上面段落的空隙

        // 表格列创建并赋值
        cell = new PdfPCell(new Phrase("单位名称：测试有限公司", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 居中
        cell.disableBorderSide(13);// 去除左右上边框，保留下边框
        cell.setColspan(4);// 合并列数
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("日期：2020-06-05", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.disableBorderSide(13);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("单位（元）", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.disableBorderSide(13);
        cell.setColspan(1);
        table.addCell(cell);
        // 首行
        cell = new PdfPCell(new Phrase("期间", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("月份", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("分类", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("年利率", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("日利率", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("基数", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("利息", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("起始日：2020-04-26\n" + "结束日：2020-05-25", font));
        cell.setPadding(16f);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("4", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("资金", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1.10%", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("0.000031", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("10598164.91", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("325.01", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("合计", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(7);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("325.01", font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("会计制单:", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.disableBorderSide(14);
        cell.setColspan(4);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("复核:", font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.disableBorderSide(14);
        cell.setColspan(4);
        table.addCell(cell);
        table.setSpacingBefore(16f);
        document.add(table);

        document.close();
    }
}
