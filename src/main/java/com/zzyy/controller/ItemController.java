package com.zzyy.controller;


import com.alibaba.fastjson.JSONObject;
import com.zzyy.DruidConfiguration;
import com.zzyy.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    private Logger logger = LogManager.getLogger(DruidConfiguration.class);

    @Autowired
    ItemService itemService;

    @RequestMapping("/test")
    public String test() {
        return "hello!";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addItem() {

        itemService.addItem();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public JSONObject list() {
        logger.info("查询list");
        return itemService.list();
    }
}
