package com.zzyy.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Auther: zhouyu
 * @Date: 2019/4/23 21:36
 * @Description:
 */
public class PoiUtils {


//    public static void main(String[] args) {
//        Workbook wb = null;
//        Sheet sheet = null;
//        Row row = null;
//        List<Map<String, String>> list = null;
//        String filePath = "C:\\Users\\Administrator\\Desktop\\没有导入服务商品的会员  共62条.xlsx";
//        wb = readExcel(filePath);
//        if (wb != null) {
//            //用来存放表中数据
//            list = new ArrayList<Map<String, String>>();
//            //获取第一个sheet
//            sheet = wb.getSheetAt(2);
//            //获取最大行数
//            int rownum = sheet.getPhysicalNumberOfRows();
//            //获取第一行
//            row = sheet.getRow(0);
//            //获取最大列数
//            for (int i = 1; i < rownum; i++) {
//                Map<String, String> map = new LinkedHashMap<String, String>();
//                row = sheet.getRow(i);
//                if (row != null) {
//                    String memberId = (String) getCellFormatValue(row.getCell(4));
//                    String itemId = (String) getCellFormatValue(row.getCell(6));
//                    String times = (String) getCellFormatValue(row.getCell(10));
//                    String amount = (String) getCellFormatValue(row.getCell(15));
//                    String price = (String) getCellFormatValue(row.getCell(14));
//                    String beginTime = "'" + (String) getCellFormatValue(row.getCell(11)) + "'";
//                    String invalidTime = "'" + (String) getCellFormatValue(row.getCell(12)) + "'";
//                    long uid = UIDUtils.getUID();
//                    if (StringUtils.isNotBlank(memberId)) {
//                        System.out.println("INSERT INTO t_ts_account(`fdbid`,`fid`,`fstoreid`,`fmemberid`,`fitemid`,`ftimes`,`fgiftTimes`,`fgiftDates`,\n" +
//                                "`famount`,`fprice`,`fbegintime`,`finvalidtime`,`fcreatetime`,`fmodifytime`,`fstatus`,`fremark`,`faccountType`)\n" +
//                                "VALUE(798498391049," + uid + ",1298296009235821," + memberId + "," + itemId + "," + times + ",0,0,\n" +
//                                amount + "," + price + "," + beginTime + "," + invalidTime + ",NULL,NULL,1,'导入账户',2);");
//                    }
//                }
//            }
//        }
//        //遍历解析出来的list
//        for (Map<String, String> map : list) {
//            for (Entry<String, String> entry : map.entrySet()) {
//                System.out.print(entry.getKey() + ":" + entry.getValue() + ",");
//            }
//            System.out.println();
//        }
//
//    }


    public static void main(String[] args) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        String filePath = "C:\\Users\\Administrator\\Desktop\\没有导入服务商品的会员  共62条.xlsx";
        String columns[] = {"name", "age", "score"};
        wb = readExcel(filePath);
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<Map<String, String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(3);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if (row != null) {
                    String memberId = (String) getCellFormatValue(row.getCell(4));
                    String itemId = (String) getCellFormatValue(row.getCell(6));
                    String times = "100000";
                    String amount = (String) getCellFormatValue(row.getCell(7));
                    String price = "0";
                    String beginTime = "'"+(String) getCellFormatValue(row.getCell(11))+"'";
                    String invalidTime = "'"+(String) getCellFormatValue(row.getCell(12))+"'";
                    long uid = UIDUtils.getUID();
                    if (StringUtils.isNotBlank(memberId)){
                        System.out.println("INSERT INTO t_ts_account(`fdbid`,`fid`,`fstoreid`,`fmemberid`,`fitemid`,`ftimes`,`fgiftTimes`,`fgiftDates`,\n" +
                                "`famount`,`fprice`,`fbegintime`,`finvalidtime`,`fcreatetime`,`fmodifytime`,`fstatus`,`fremark`,`faccountType`)\n" +
                                "VALUE(798498391049," + uid + ",1298296009235821," + memberId + "," + itemId + "," + times + ",0,0,\n" +
                                amount + "," + price + "," + beginTime + "," + invalidTime + ",NULL,NULL,1,'导入账户',1);");
                    }
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Workbook wb = null;
//        Sheet sheet = null;
//        Row row = null;
//        List<Map<String, String>> list = null;
//        String filePath = "C:\\Users\\Administrator\\Desktop\\没有导入服务商品的会员  共62条.xlsx";
//        wb = readExcel(filePath);
//        if (wb != null) {
//            //用来存放表中数据
//            list = new ArrayList<Map<String, String>>();
//            //获取第一个sheet
//            sheet = wb.getSheetAt(2);
//            //获取最大行数
//            int rownum = sheet.getPhysicalNumberOfRows();
//            //获取第一行
//            row = sheet.getRow(0);
//            //获取最大列数
//            int colnum = row.getPhysicalNumberOfCells();
//            for (int i = 1; i < rownum; i++) {
//                Map<String, String> map = new LinkedHashMap<String, String>();
//                row = sheet.getRow(i);
//                if (row != null) {
//                    String memberId = (String) getCellFormatValue(row.getCell(4));
//                    String itemId = (String) getCellFormatValue(row.getCell(6));
//                    String amount = (String) getCellFormatValue(row.getCell(7));
//                    String times = (String) getCellFormatValue(row.getCell(8));
//                    String saleTimes = (String) getCellFormatValue(row.getCell(9));
//                    if (StringUtils.isBlank(saleTimes)){
//                        saleTimes = "0";
//                    }
//                    String currentTimes = (String) getCellFormatValue(row.getCell(10));
//                    String saleAmount = (String) getCellFormatValue(row.getCell(16));
//                    String currentAmount = (String) getCellFormatValue(row.getCell(15));
//                    long uid = UIDUtils.getUID();
//                    if (StringUtils.isNotBlank(memberId)){
//                        System.out.println("INSERT INTO t_ts_accumulate(`fdbid`,`fid`,`fmemberid`,`fitemid`,`frechargemoney`,`frechargetimes`,`fsalemoney`,`fsaletimes`,\n" +
//                                "`ftotalmoney`,`ftotaltimes`)" +
//                                "VALUE(798498391049," + uid + "," + memberId + "," + itemId + "," + amount + ","+ times + ","+ saleAmount +","+ saleTimes +","+
//                                currentAmount + "," + currentTimes + ");");
//                    }
//                }
//            }
//        }
//    }

//    public static void main(String[] args) {
//        Workbook wb = null;
//        Sheet sheet = null;
//        Row row = null;
//        List<Map<String, String>> list = null;
//        String cellData = null;
//        String filePath = "C:\\Users\\Administrator\\Desktop\\没有导入服务商品的会员  共62条.xlsx";
//        String columns[] = {"name", "age", "score"};
//        wb = readExcel(filePath);
//        if (wb != null) {
//            //用来存放表中数据
//            list = new ArrayList<Map<String, String>>();
//            //获取第一个sheet
//            sheet = wb.getSheetAt(3);
//            //获取最大行数
//            int rownum = sheet.getPhysicalNumberOfRows();
//            //获取第一行
//            row = sheet.getRow(0);
//            //获取最大列数
//            int colnum = row.getPhysicalNumberOfCells();
//            for (int i = 1; i < rownum; i++) {
//                Map<String, String> map = new LinkedHashMap<String, String>();
//                row = sheet.getRow(i);
//                if (row != null) {
//                    String memberId = (String) getCellFormatValue(row.getCell(4));
//                    String itemId = (String) getCellFormatValue(row.getCell(6));
//                    String amount = (String) getCellFormatValue(row.getCell(7));
//                    String times = "100000";
//                    String saleTimes = "0";
//                    String currentTimes = "100000";
//                    String saleAmount = "0";
//                    String currentAmount = (String) getCellFormatValue(row.getCell(7));
//                    long uid = UIDUtils.getUID();
//                    if (StringUtils.isNotBlank(memberId)){
//                        System.out.println("INSERT INTO t_ts_accumulate(`fdbid`,`fid`,`fmemberid`,`fitemid`,`frechargemoney`,`frechargetimes`,`fsalemoney`,`fsaletimes`,\n" +
//                                "`ftotalmoney`,`ftotaltimes`)" +
//                                "VALUE(798498391049," + uid + "," + memberId + "," + itemId + "," + amount + ","+ times + ","+ saleAmount +","+ saleTimes +","+
//                                currentAmount + "," + currentTimes + ");");
//                    }
//                }
//            }
//        }
//    }

    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    DecimalFormat df = new DecimalFormat("0");
                    cellValue = df.format(cell.getNumericCellValue());
//                    double numericCellValue = cell.getNumericCellValue();
//                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }


}
