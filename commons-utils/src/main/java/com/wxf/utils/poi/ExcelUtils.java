package com.wxf.utils.poi;

import com.alibaba.fastjson.util.TypeUtils;
import com.wxf.utils.poi.annotation.EnableExport;
import com.wxf.utils.poi.annotation.EnableExportField;
import com.wxf.utils.poi.annotation.EnableSelectList;
import com.wxf.utils.poi.annotation.ImportIndex;
import com.wxf.utils.poi.enums.ColorEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtils {
    /**
     * 所有的下拉列表数据存在这个map中，key是对应的Excel列的序号，从0开始，value为下拉列表键对值
     **/
    public static final Map<Integer, Map<String, String>> ALL_SELECT_LIST_MAP = new HashMap<Integer, Map<String, String>>();

    /**
     * 将Excel转换为对象集合
     *
     * @param excel Excel 文件
     * @param clazz pojo类型
     */
    public static List<?> parseExcelToList(File excel, Class<?> clazz) {
        List<Object> res = new ArrayList<Object>();
        // 创建输入流，读取Excel
        InputStream is = null;
        HSSFSheet sheet = null;
        try {
            is = Files.newInputStream(Paths.get(excel.getAbsolutePath()));
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(is));
            // 默认只获取第一个工作表
            sheet = workbook.getSheetAt(0);
            if (sheet != null) {
                int i = 2;
                String[] values;
                HSSFRow row = sheet.getRow(i);
                while (row != null) {
                    // 获取单元格数目
                    int cellNum = row.getPhysicalNumberOfCells();
                    values = new String[cellNum];
                    for (int j = 0; j <= cellNum; j++) {
                        HSSFCell cell = row.getCell((short) j);
                        if (cell != null) {
                            // 设置单元格内容类型
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            // 获取单元格值
                            String value = cell.getStringCellValue() == null ? null : cell.getStringCellValue();
                            values[j] = value;
                        }
                    }
                    Field[] fields = clazz.getDeclaredFields();
                    Object obj = clazz.newInstance();
                    for (Field f : fields) {
                        if (f.isAnnotationPresent(ImportIndex.class)) {
                            ImportIndex annotation = f.getAnnotation(ImportIndex.class);
                            int index = annotation.index();
                            String useSetMethodName = annotation.useSetMethodName();
                            if (!"".equals(useSetMethodName)) {
                                Object val = TypeUtils.cast(values[index], f.getType(), null);
                                f.setAccessible(true);
                                Method method = clazz.getMethod(useSetMethodName, f.getType(), Object.class);
                                method.setAccessible(true);
                                method.invoke(obj, f.get(obj), val);
                            } else {
                                f.setAccessible(true);
                                Object val = TypeUtils.cast(values[index], f.getType(), null);
                                f.set(obj, val);
                            }
                        }
                    }
                    res.add(obj);
                    i++;
                    row = sheet.getRow(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将Excel转换为对象集合
     *
     * @param excel Excel 文件输入流
     * @param clazz pojo类型
     */
    public static List<?> parseExcelToList(InputStream excel, Class<?> clazz) throws Exception {
        List<Object> res = new ArrayList<Object>();
        // 创建输入流，读取Excel
        InputStream is = null;
        HSSFSheet sheet = null;

        is = excel;
        if (is != null) {
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(is));
            // 默认只获取第一个工作表
            sheet = workbook.getSheetAt(0);
            if (sheet != null) {
                int i = 2;
                String[] values;
                HSSFRow row = sheet.getRow(i);
                while (row != null) {
                    // 获取单元格数目
                    int cellNum = row.getPhysicalNumberOfCells();
                    values = new String[cellNum];
                    for (int j = 0; j <= cellNum; j++) {
                        HSSFCell cell = row.getCell((short) j);
                        if (cell != null) {
                            // 设置单元格内容类型
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            // 获取单元格值
                            String value = cell.getStringCellValue() == null ? null : cell.getStringCellValue();
                            values[j] = value;
                        }
                    }
                    Field[] fields = clazz.getDeclaredFields();
                    Object obj = clazz.newInstance();
                    for (Field f : fields) {
                        if (f.isAnnotationPresent(ImportIndex.class)) {

                            ImportIndex annotation = f.getAnnotation(ImportIndex.class);
                            int index = annotation.index();
                            Object value = values[index];
                            if (f.isAnnotationPresent(EnableSelectList.class)) {

                                value = getKeyByValue(ALL_SELECT_LIST_MAP.get(index), String.valueOf(value));

                            }
                            String useSetMethodName = annotation.useSetMethodName();
                            if (!"".equals(useSetMethodName)) {
                                Object val = TypeUtils.cast(value, f.getType(), null);
                                f.setAccessible(true);
                                Method method = clazz.getMethod(useSetMethodName, f.getType(), Object.class);
                                method.setAccessible(true);
                                method.invoke(obj, f.get(obj), val);
                            } else {
                                f.setAccessible(true);
                                Object val = TypeUtils.cast(value, f.getType(), null);
                                f.set(obj, val);
                            }

                        }
                    }
                    res.add(obj);
                    i++;
                    row = sheet.getRow(i);
                }
            }
        }

        return res;
    }

    /**
     * 导出 Excel
     *
     * @param outputStream  输出流，用于写文件
     * @param dataList      需要导出的数据
     * @param clazz         导出数据的pojo类型
     * @param selectListMap 下拉列表的列
     * @param exportTitle   当该参数不为空则替换默认的标题
     */
    public static void exportExcel(
            OutputStream outputStream, List dataList,
            Class<?> clazz, Map<Integer, Map<String, String>> selectListMap,
            String exportTitle) {
        // 创建一个Excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 建立表
        HSSFSheet hssfsheet = workbook.createSheet();
        hssfsheet.setDefaultRowHeight((short) (20 * 20));
        // 检查当前pojo是否允许导出
        if (clazz.isAnnotationPresent(EnableExport.class)) {
            EnableExport export = clazz.getAnnotation(EnableExport.class);
            // 获取所有标题名称
            List<String> colNames = new ArrayList<String>();
            // 获取所有标题的背景颜色
            List<ColorEnum> colors = new ArrayList<ColorEnum>();
            // 所有允许导出的字段
            List<Field> fieldList = new ArrayList<Field>();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(EnableExportField.class)) {
                    EnableExportField enableExportField = field.getAnnotation(EnableExportField.class);
                    colNames.add(enableExportField.colName());
                    colors.add(enableExportField.cellColor());
                    fieldList.add(field);
                }
            }
            // 设置每列的宽度
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                hssfsheet.setColumnWidth((short) i, (short) (field.getAnnotation(EnableExportField.class).colWidth() * 20));
            }

            HSSFRow hssfRow = hssfsheet.createRow(0);
            HSSFCell hssfcell = hssfRow.createCell((short) 0);

            // 绘制表头以及菜单
            String fileName = export.fileName();
            if (exportTitle != null) {
                fileName = exportTitle;
            }
            // 绘制标题
            createTitle(workbook, hssfRow, hssfcell, hssfsheet, colNames.size() - 1, fileName, export.cellColor());
            // 创建标题行（表头）
            createHeadRow(workbook, hssfRow, hssfcell, hssfsheet, colNames, colors);
            try {
                // 表格样式
                HSSFCellStyle cellStyle = getBasicCellStyle(workbook);
                // 插入内容
                int i = 0;
                for (Object obj : dataList) {
                    hssfRow = hssfsheet.createRow(i + 2);
                    // 设置每列的宽度
                    // 此处设置j=-1 ：添加一列，序号列
                    for (int j = 0; j < fieldList.size(); j++) {
                        Field field = fieldList.get(j);
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        EnableExportField enableExportField = field.getAnnotation(EnableExportField.class);
                        String getMethodName = enableExportField.useGetMethod();
                        if (!"".equals(getMethodName)) {
                            Method method = clazz.getMethod(getMethodName, field.getType());
                            method.setAccessible(true);
                            value = method.invoke(obj, value);
                        }
                        if (field.isAnnotationPresent(EnableSelectList.class)) {
                            if (selectListMap != null && selectListMap.get(j) != null)
                                value = selectListMap.get(j).get(value);
                        }
                        setCellValue(value, hssfcell, hssfRow, cellStyle, j);
                    }
                    i++;
                }
                // 创建下拉列表
                // createDataValidation(hssfsheet, selectListMap);
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取一个基本的带边框的单元格
     */
    private static HSSFCellStyle getBasicCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        hssfcellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle.setWrapText(true);
        return hssfcellstyle;
    }

    /**
     * 获取带有背景色的标题单元格
     */
    private static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook, ColorEnum color) {
        HSSFCellStyle hssfcellstyle = getBasicCellStyle(workbook);
        hssfcellstyle.setFillForegroundColor(color.getIndex()); // 设置背景色
        hssfcellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return hssfcellstyle;
    }

    /**
     * 创建一个跨列的标题行
     */
    private static void createTitle(
            HSSFWorkbook workbook, HSSFRow hssfRow,
            HSSFCell hssfcell, HSSFSheet hssfsheet,
            int allColNum, String title, ColorEnum color) {
        // 在sheet里增加合并单元格
        // CellRangeAddress cra = new CellRangeAddress(0, 0, 0, allColNum);
        Region region = new Region(0, (short) 0, 0, (short) allColNum);
        hssfsheet.addMergedRegion(region);
        // // 使用RegionUtil类为合并后的单元格添加边框
        // RegionUtil.setBorderBottom(1, cra, hssfsheet, workbook); // 下边框
        // RegionUtil.setBorderLeft(1, cra, hssfsheet, workbook); // 左边框
        // RegionUtil.setBorderRight(1, cra, hssfsheet, workbook); // 有边框
        // RegionUtil.setBorderTop(1, cra, hssfsheet, workbook); // 上边框

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框

        // 设置表头
        hssfRow = hssfsheet.getRow(0);
        hssfcell = hssfRow.getCell((short) 0);
        hssfcell.setCellStyle(getTitleCellStyle(workbook, color));
        hssfcell.setCellStyle(cellStyle);
        hssfcell.setEncoding(HSSFCell.ENCODING_UTF_16);
        hssfcell.setCellValue(title);
    }

    /**
     * 设置表头标题栏以及表格高度
     */
    private static void createHeadRow(
            HSSFWorkbook workbook, HSSFRow hssfRow,
            HSSFCell hssfcell, HSSFSheet hssfsheet,
            List<String> colNames, List<ColorEnum> colors) {
        // 插入标题行
        hssfRow = hssfsheet.createRow(1);
        for (int i = 0; i < colNames.size(); i++) {
            hssfcell = hssfRow.createCell((short) i);
            hssfcell.setCellStyle(getTitleCellStyle(workbook, colors.get(i)));
            hssfcell.setCellType(HSSFCell.CELL_TYPE_STRING);
            hssfcell.setCellValue(colNames.get(i));
        }
    }

    /**
     * excel添加下拉数据校验
     *
     * @param sheet 哪个 sheet 页添加校验
     * @return
     */
 /*   public static void createDataValidation(
            HSSFSheet sheet, Map<Integer, Map<String, String>> selectListMap) {
        if (selectListMap != null) {
            for (Map.Entry<Integer, Map<String, String>> entry : selectListMap.entrySet()) {
                Integer key = entry.getKey();
                Map<String, String> value = entry.getValue();
                // 第几列校验（0开始）key 数据源数组value
                if (value.size() > 0) {
                    int i = 0;
                    String[] valueArr = new String[value.size()];
                    for (Map.Entry<String, String> ent : value.entrySet()) {
                        valueArr[i] = ent.getValue();
                        i++;
                    }
                    CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(2, 65535, key, key);
                    DataValidationHelper helper = sheet.getDataValidationHelper();
                    DataValidationConstraint constraint = helper.createExplicitListConstraint(valueArr);
                    DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
                    //处理Excel兼容性问题
                    if (dataValidation instanceof XSSFDataValidation) {
                        dataValidation.setSuppressDropDownArrow(true);
                        dataValidation.setShowErrorBox(true);
                    } else {
                        dataValidation.setSuppressDropDownArrow(false);
                    }
                    dataValidation.setEmptyCellAllowed(true);
                    dataValidation.setShowPromptBox(true);
                    dataValidation.createPromptBox("提示", "只能选择下拉框里面的数据");
                    sheet.addValidationData(dataValidation);
                }
            }
        }
    }*/


    /**
     * 通过value获取key值
     */
    private static String getKeyByValue(Map<String, String> selectMap, String value) {
        if (selectMap != null) {
            for (Map.Entry<String, String> ent : selectMap.entrySet()) {
                if (value != null && value.equals(ent.getValue())) {
                    return ent.getKey();
                }
            }
        } else {
            return value;
        }
        return null;
    }


    /**
     * 判断字符串是否为数字
     */
    private static boolean isNumeric(String str) {
        Pattern pattren =
                Pattern.compile("[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        if (str != null && !"".equals(str.trim())) {
            Matcher matcher = pattren.matcher(str);
            if (matcher.matches()) {
                return str.contains(".") || !str.startsWith("0");
            }
        }
        return false;
    }


    /**
     * 设置单元格的值
     */
    private static void setCellValue(Object value, HSSFCell hssfcell, HSSFRow hssfRow, HSSFCellStyle cellStyle, int cellIndex) {
        String valueStr = String.valueOf(value);
        hssfcell = hssfRow.createCell((short) cellIndex);
        // 暂时认为数字类型不会有下拉列表
        if (isNumeric(valueStr)) {
            hssfcell.setCellStyle(cellStyle);
            hssfcell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            hssfcell.setCellValue(Double.parseDouble(valueStr));
        } else {
            hssfcell.setCellStyle(cellStyle);
            hssfcell.setCellType(HSSFCell.CELL_TYPE_STRING);
            hssfcell.setCellValue(valueStr);
        }
    }
}


