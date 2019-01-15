package com.jas.test;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUtil {
    public static List<LinkedHashMap<String, String>> importExcel(InputStream inputStream, String fileName)
            throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Workbook book = createWorkBookByFileName(inputStream, fileName);

        return getContentsByWorkbook(book);
    }
    public static Map<Integer, String> getExcelTitleMap(Row title) {
        // 将标题的文字内容放入到一个map中。
        Map<Integer, String> titlemap = new HashMap<Integer, String>();
        // 得到第一行的所有列
        Iterator<Cell> cellTitle = title.cellIterator();

        // 从标题第一列开始
        int i = 0;
        // 循环标题所有的列
        while (cellTitle.hasNext()) {
            Cell cell = cellTitle.next();

            /**
             * 处理：Excel异常Cannot get a text value from a numeric cell
             * 将所有列中的内容都设置成String类型格式
             */
            if (cell != null) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }

            String value = cell.getStringCellValue();
            // 特殊字符过滤
            //过滤单元格的首尾空格 以及中英文单引号
            value = value.trim().replace("‘", "").replace("’", "").replace("\'", "");
            titlemap.put(i, value);
            i = i + 1;
        }
        return titlemap;
    }
    public static List<LinkedHashMap<String, String>> getContentsByWorkbook(Workbook book)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<LinkedHashMap<String, String>> result = new ArrayList<>();
        if (book != null) {
            Sheet sheet = book.getSheetAt(0);
            // 空表格
            Integer rowCount = sheet.getLastRowNum();
            if (rowCount == 0) {
                throw new RuntimeException("Excel内容为空");
            }

            // // 得到第一页的所有行
            Iterator<Row> row = sheet.rowIterator();
            // 得到第二行，也就是标题行
            Row title = row.next();
            // 得到第一行的所有列 <0,  姓名> <1, 手机>
            Map<Integer, String> titleMap = getExcelTitleMap(title);

            while (row.hasNext()) {
                Row rown = row.next();
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                for (int i = 0; i < titleMap.size(); i++) {
                    String titleString = titleMap.get(i);
                    Cell cell = rown.getCell(i, Row.CREATE_NULL_AS_BLANK);
                    String str = getCellContentAsString(cell);

                    // 特殊字符过滤
                    str = str.trim().replace("‘", "").replace("’", "").replace("\'", "");
                    map.put(titleString, str);
                }

                result.add(map);
            }
        }


        return result;
    }

    public static String getCellContentAsString(Cell cell) {
        if (null == cell) {
            return "";
        }
        String result = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
//                format 调试excel 单元格解析 使用
//                short format = cell.getCellStyle().getDataFormat();
//                System.out.println("format:" + format);
//                System.out.println("formatString:" + cell.getCellStyle().getDataFormatString());

                String temp = cell.getCellStyle().getDataFormatString();

                SimpleDateFormat sdf = null;

                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 处理日期格式、时间格式
                    if (((temp.contains("y") || temp.contains("d")) && !temp.contains("h")) || temp.contains("mmmmm;@")) {
                        //日期
                        sdf = new SimpleDateFormat("yyyy/MM/dd");
                    } else if ((temp.contains("时") && temp.contains("分")) || (temp.contains("h:mm")) && !temp.contains("yy")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {
                        // 日期时间
                        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    }

                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else {
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    if (temp != null) {
                        if (((temp.contains("y") || temp.contains("d")) && !temp.contains("h") && temp.contains("m")) || temp.contains("aaa;@")) {
                            //日期
                            sdf = new SimpleDateFormat("yyyy/MM/dd");
                            Date date = cell.getDateCellValue();
                            return sdf.format(date);

                        } else if ((temp.contains("时") && temp.contains("分")) || (temp.contains("h:mm")) && !temp.contains("yy")) {
                            sdf = new SimpleDateFormat("HH:mm");
                            Date date = cell.getDateCellValue();
                            return sdf.format(date);

                        } else if (temp.contains("yy") && temp.contains("h:mm")) {
                            // 日期时间
                            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date date = cell.getDateCellValue();
                            return sdf.format(date);

                        } else {
                            decimalFormat.applyPattern("###################.######");
                        }

                    }
                    double value = cell.getNumericCellValue();
                    result = decimalFormat.format(value);
                }
                break;
            case Cell.CELL_TYPE_STRING:
                result = StringUtils.isEmpty(String.valueOf(cell.getStringCellValue()))? "" :String.valueOf(cell.getStringCellValue()).trim();
                break;

            // 公式解析
            case Cell.CELL_TYPE_FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        result = String.valueOf(cell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        result = "" + cell.getRichStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        result = "" + cell.getBooleanCellValue();
                    default:
                        break;
                }
                break;

            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                break;
            default:
                break;
        }
        return result;
    }


    public static Workbook createWorkBookByFileName(InputStream inputStream, String fileName) throws IOException {

        if (fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".et") || fileName.toLowerCase().endsWith(".ett"))
            try {
                return new HSSFWorkbook(inputStream);
            } catch (EncryptedDocumentException e) {
                throw new RuntimeException("文件类型不合法：" + fileName);
            }
        else if (fileName.toLowerCase().endsWith(".xlsx")) {
            byte[] bys = stream2Byte(inputStream);
            try {
                new POIFSFileSystem(byte2InputStream(bys));
            } catch (OfficeXmlFileException e) {
                throw new RuntimeException("文件类型不合法：" + fileName);
            }
        } else {
            throw new RuntimeException("文件类型不合法：" + fileName);
        }
        return null;
    }
    public static byte[] stream2Byte(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = is.read(b, 0, b.length)) != -1) {
            baos.write(b, 0, len);
        }
        byte[] buffer = baos.toByteArray();
        return buffer;
    }

    public static InputStream byte2InputStream(byte[] b) throws IOException {
        InputStream is = new ByteArrayInputStream(b);
        return is;
    }
}
