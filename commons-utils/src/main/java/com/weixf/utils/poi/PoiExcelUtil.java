package com.weixf.utils.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class PoiExcelUtil {

    // 设置cell编码解决中文高位字节截断
    private static final short XLS_ENCODING = HSSFCell.ENCODING_UTF_16;

    // 定制浮点数格式
    private static final String NUMBER_FORMAT = "#,##0.00";

    // 定制日期格式
    private static final String DATE_FORMAT = "m/d/yy"; // "m/d/yy h:mm"

    // book [includes sheet]
    private HSSFWorkbook wb = null;

    private HSSFSheet sheet = null;

    private HSSFRow row = null;

    // 第sheetNum个工作表
    private int sheetNum = 0;

    private int rowNum = 0;

    private File file = null;

    private OutputStream out = null;

    private HSSFWorkbook workbook = null;

    public PoiExcelUtil() {
    }

    public static void main(String[] args) throws Exception {

        PoiExcelUtil poiExcelUtil = new PoiExcelUtil();
        File file = new File("test.xls");
        try {
            poiExcelUtil.initWrite(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HSSFCellStyle cellStyle = poiExcelUtil.createCellStyle();

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor((short) 22);

        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框

        HSSFFont font = poiExcelUtil.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);

        poiExcelUtil.createRow(0);
        poiExcelUtil.setCell(0, "1", cellStyle, 2000);
        poiExcelUtil.setCell(1, "2", cellStyle, 5000);
        poiExcelUtil.setCell(2, "3", cellStyle, 8000);
        poiExcelUtil.setCell(3, "4", cellStyle, 4000);
        poiExcelUtil.setCell(4, "5", cellStyle, 8000);
        poiExcelUtil.setCell(5, "6", cellStyle, 4000);
        poiExcelUtil.setCell(6, "7", cellStyle, 4000);
        poiExcelUtil.setCell(7, "8", cellStyle, 4000);

        HSSFCellStyle cellStyle2 = poiExcelUtil.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
        for (int i = 0; i < 100; i++) {
            poiExcelUtil.createRow(i + 1);
            poiExcelUtil.setCell(0, i + 1, cellStyle2);
            poiExcelUtil.setCell(1, "11", cellStyle2);
            poiExcelUtil.setCell(2, "12", cellStyle2);
            poiExcelUtil.setCell(3, "13", cellStyle2);
            poiExcelUtil.setCell(4, "14", cellStyle2);
            poiExcelUtil.setCell(5, "15", cellStyle2);
            poiExcelUtil.setCell(6, "16", cellStyle2);
            poiExcelUtil.setCell(7, "17", cellStyle2);
        }
        poiExcelUtil.export();
    }

    /*
     * 初始化 read
     */
    public void initRead(File file) {
        this.file = file;
    }

    /**
     * 初始化write
     */
    public void initWrite(OutputStream out) {
        this.out = out;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setSheetNum(int sheetNum) {
        this.sheetNum = sheetNum;
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 读取excel文件获得HSSFWorkbook对象
     */
    public void open() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        wb = new HSSFWorkbook(new POIFSFileSystem(fis));
        fis.close();
    }

    /**
     * 返回sheet表数目
     *
     * @return int
     */
    public int getSheetCount() {
        int sheetCount = -1;
        sheetCount = wb.getNumberOfSheets();
        return sheetCount;
    }

    /**
     * sheetNum下的记录行数
     *
     * @return int
     */
    public int getRowCount() {
        if (wb == null) {
            System.out.println("=============>WorkBook为空");
        }
        HSSFSheet sheet = wb.getSheetAt(this.sheetNum);
        int rowCount = -1;
        rowCount = sheet.getLastRowNum();
        return rowCount;
    }

    /**
     * 读取指定sheetNum的rowCount
     *
     * @param sheetNum 工作表
     * @return int
     */
    public int getRowCount(int sheetNum) {
        HSSFSheet sheet = wb.getSheetAt(sheetNum);
        int rowCount = -1;
        rowCount = sheet.getLastRowNum();
        return rowCount;
    }

    /**
     * 得到指定行的内容
     *
     * @param lineNum 行
     * @return String[]
     */
    public String[] readExcelLine(int lineNum) {
        return readExcelLine(this.sheetNum, lineNum);
    }

    /**
     * 指定工作表和行数的内容
     *
     * @param sheetNum 工作表
     * @param lineNum  行数
     * @return String[]
     */
    public String[] readExcelLine(int sheetNum, int lineNum) {
        if (sheetNum < 0 || lineNum < 0)
            return null;
        String[] strExcelLine = null;
        try {
            sheet = wb.getSheetAt(sheetNum);
            row = sheet.getRow(lineNum);

            int cellCount = row.getLastCellNum();
            strExcelLine = new String[cellCount + 1];
            for (int i = 0; i <= cellCount; i++) {
                strExcelLine[i] = readStringExcelCell(lineNum, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strExcelLine;
    }

    /**
     * 读取指定列的内容
     *
     * @param cellNum 列
     * @return String
     */
    public String readStringExcelCell(int cellNum) {
        return readStringExcelCell(this.rowNum, cellNum);
    }

    /**
     * 指定行和列编号的内容
     *
     * @param rowNum  行号
     * @param cellNum 列
     * @return String
     */
    public String readStringExcelCell(int rowNum, int cellNum) {
        return readStringExcelCell(this.sheetNum, rowNum, cellNum);
    }

    /**
     * 指定工作表、行、列下的内容
     *
     * @param sheetNum 工作表
     * @param rowNum   行
     * @param cellNum  列
     * @return String
     */
    public String readStringExcelCell(int sheetNum, int rowNum, int cellNum) {
        if (sheetNum < 0 || rowNum < 0)
            return "";
        String strExcelCell = "";
        try {
            sheet = wb.getSheetAt(sheetNum);
            row = sheet.getRow(rowNum);

            if (row.getCell((short) cellNum) != null) {
                switch (row.getCell((short) cellNum).getCellType()) {
                    case HSSFCell.CELL_TYPE_FORMULA:
                        strExcelCell = "FORMULA ";
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC: {
                        strExcelCell = String.valueOf(row.getCell((short) cellNum).getNumericCellValue());
                    }
                    break;
                    case HSSFCell.CELL_TYPE_STRING:
                        strExcelCell = row.getCell((short) cellNum).getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        strExcelCell = "";
                        break;
                    default:
                        strExcelCell = "";
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strExcelCell;
    }

    /**
     * 导出Excel文件
     */
    public void export() throws Exception {
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new Exception(" 生成导出Excel文件出错! ", e);
        } catch (IOException e) {
            throw new Exception(" 写入Excel文件出错! ", e);
        }

    }

    /**
     * 增加一行
     *
     * @param index 行号
     */
    public void createRow(int index) {
        this.row = this.sheet.createRow(index);
    }

    /**
     * 获取单元格的值
     *
     * @param index 列号
     */
    public String getCell(int index) {
        HSSFCell cell = this.row.getCell((short) index);
        String strExcelCell = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    strExcelCell = "FORMULA ";
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    strExcelCell = String.valueOf(cell.getNumericCellValue());
                }
                break;
                case HSSFCell.CELL_TYPE_STRING:
                    strExcelCell = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    strExcelCell = "";
                    break;
                default:
                    strExcelCell = "";
                    break;
            }
        }
        return strExcelCell;
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, int value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, double value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
        // 建立新的cell样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        // 设置cell样式为定制的浮点数格式
        cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));
        // 设置该cell浮点数的显示格式
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, String value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(XLS_ENCODING);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, Calendar value) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setEncoding(XLS_ENCODING);
        cell.setCellValue(value.getTime());
        // 建立新的cell样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置cell样式为定制的日期格式
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT));
        // 设置该cell日期的显示格式
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, int value, HSSFCellStyle style) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(XLS_ENCODING);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, String value, HSSFCellStyle style) {
        HSSFCell cell = this.row.createCell((short) index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(XLS_ENCODING);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    /**
     * 设置单元格
     *
     * @param index 列号
     * @param value 单元格填充值
     */
    public void setCell(int index, String value, HSSFCellStyle style, int width) {
        HSSFCell cell = this.row.createCell((short) index);
        this.sheet.setColumnWidth((short) index, (short) width);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setEncoding(XLS_ENCODING);
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    /**
     * 合并单元格
     *
     * @param x1 起始行
     * @param y1 起始列
     * @param x2 终止行
     * @param y2 终止列
     */
    public void merge(int x1, int y1, int x2, int y2) {
        this.sheet.addMergedRegion(new Region((short) x1, (short) y1, (short) x2, (short) y2));
    }

    private HSSFCellStyle createCellStyle() {
        return workbook.createCellStyle();
    }

    private HSSFFont createFont() {
        return workbook.createFont();
    }

    public void setDefaultHeight(int height) {
        this.sheet.setDefaultRowHeight((short) height);
    }
}

