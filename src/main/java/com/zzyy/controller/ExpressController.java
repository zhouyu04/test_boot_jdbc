package com.zzyy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzyy.entity.Express;
import com.zzyy.service.ExpressService;
import com.zzyy.utils.KdApiOrderDistinguish;
import com.zzyy.utils.KdniaoTrackQueryAPI;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhouyu
 * @Date: 2019/6/4 18:38
 * @Description:
 */
@Controller
@RequestMapping("/express")
public class ExpressController {
    private static Logger logger = LoggerFactory.getLogger(ExpressController.class);


    @Autowired
    ExpressService expressService;

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    @RequestMapping("/toTest")
    public String toTest() {
        return "express/query";
    }


    @RequestMapping("/upload")
    public Object springUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        Long id = 10086L;
        expressService.delete(id);

        JSONObject result = new JSONObject();
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

        KdApiOrderDistinguish queryOrder = new KdApiOrderDistinguish();
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        for (int i =0;i< arrays.size();i++){
            String logisticCode = arrays.get(i);

            try {
                String orderTracesByJson = queryOrder.getOrderTracesByJson(logisticCode);
                if (StringUtils.isNotBlank(orderTracesByJson)){
                    JSONObject object = JSONObject.parseObject(orderTracesByJson);

                    JSONArray shippers = object.getJSONArray("Shippers");
                    JSONObject jsonObject = new JSONObject();
                    if (shippers != null) {
                        jsonObject = shippers.getJSONObject(0);
                    }
                    String shipperCode = jsonObject.getString("ShipperCode");

                    String expressInfo = api.getOrderTracesByJson(shipperCode, logisticCode);
                    JSONObject expressJson = JSONObject.parseObject(expressInfo);

                    String logisticCode1 = expressJson.getString("LogisticCode");
                    int state = expressJson.getIntValue("State");
                    String traces = expressJson.getString("Traces");

                    Express express = new Express();
                    express.setId(id);
                    express.setExpressCode(logisticCode1);
                    express.setStatus(state);
                    express.setTrace(traces);
                    expressService.add(express);
                }
            } catch (Exception e) {
                logger.error("未知错误",e);
            }
        }
        return "express/result";
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
    public JSONObject getData(HttpServletRequest request) {

        JSONObject jsondata = new JSONObject();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Integer pageIndex = Integer.valueOf(page);
        Integer pageSize = Integer.valueOf(rows);
        int startIndex = (pageIndex - 1) * pageSize;

        Map<String,Object> params = new HashMap<>();
        params.put("startIndex",startIndex);
        params.put("pageSize",pageSize);

        List<Express> expressList = expressService.find(params);
        long count = expressService.findCount(params);

        jsondata.put("rows",expressList);
        jsondata.put("page",pageIndex);
        jsondata.put("total",pageSize);
        jsondata.put("records",count);
        return jsondata;
    }

}
