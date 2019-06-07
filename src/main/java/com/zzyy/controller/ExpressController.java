package com.zzyy.controller;

import com.alibaba.fastjson.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhouyu
 * @Date: 2019/6/4 18:38
 * @Description:
 */
@Controller
@RequestMapping("/express")
public class ExpressController {
    private static Logger logger = LoggerFactory.getLogger(ExpressController.class);

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    @RequestMapping("/toTest")
    public String toTest() {
        return "express/query";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String springUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String originalFilename = file.getOriginalFilename();

        Workbook workbook = null;

        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            logger.info(originalFilename);
        }

        List<String> arrays = readExcel(workbook);
        return null;
    }

    /**
     * 读取excel
     * @param workbook
     * @return
     */
    private List<String> readExcel(Workbook workbook) {

        List<String> billNo = new ArrayList<>();
        Sheet sheetAt = workbook.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();

        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheetAt.getRow(i);
            Cell cell = row.getCell(0);

            String stringCellValue = cell.getStringCellValue();
            billNo.add(stringCellValue);
        }
        return billNo;

    }


    @RequestMapping("/getData")
    @ResponseBody
    public String getData() {
        String jsondata = "{\"page\":\"1\"," + " \"total\":2," + " \"records\":\"13\"," + " \"rows\":" + " [" + " {" + " \"id\":\"13\"," + " \"cell\":" + " [\"13\",\"2007-10-06\",\"Client 3\",\"1000.00\",\"0.00\",\"1000.00\",null]" + " }," + " {" + " \"id\":\"12\"," + " \"cell\":" + " [\"12\",\"2007-10-06\",\"Client 2\",\"700.00\",\"140.00\",\"840.00\",null]" + " }," + " {" + " \"id\":\"11\"," + " \"cell\":" + " [\"11\",\"2007-10-06\",\"Client 1\",\"600.00\",\"120.00\",\"720.00\",null]" + " }," + " {" + " \"id\":\"10\"," + " \"cell\":" + " [\"10\",\"2007-10-06\",\"Client 2\",\"100.00\",\"20.00\",\"120.00\",null]" + " }," + " {" + " \"id\":\"9\"," + " \"cell\":" + " [\"9\",\"2007-10-06\",\"Client 1\",\"200.00\",\"40.00\",\"240.00\",null]" + " }," + " {" + " \"id\":\"8\"," + " \"cell\":" + " [\"8\",\"2007-10-06\",\"Client 3\",\"200.00\",\"0.00\",\"200.00\",null]" + " }," + " {" + " \"id\":\"7\"," + " \"cell\":" + " [\"7\",\"2007-10-05\",\"Client 2\",\"120.00\",\"12.00\",\"134.00\",null]" + " }," + " {" + " \"id\":\"6\"," + " \"cell\":" + " [\"6\",\"2007-10-05\",\"Client 1\",\"50.00\",\"10.00\",\"60.00\",\"\"]" + " }," + " {" + " \"id\":\"5\"," + " \"cell\":" + " [\"5\",\"2007-10-05\",\"Client 3\",\"100.00\",\"0.00\",\"100.00\",\"no tax at all\"]" + " }," + " {" + " \"id\":\"4\"," + " \"cell\":" + " [\"4\",\"2007-10-04\",\"Client 3\",\"150.00\",\"0.00\",\"150.00\",\"no tax\"]" + " }" + " ]," + " \"userdata\":{\"amount\":3220,\"tax\":342,\"total\":3564,\"name\":\"Totals:\"}" + " }";
        return jsondata;
    }

}
