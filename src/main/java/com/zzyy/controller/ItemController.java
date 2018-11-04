package com.zzyy.controller;


import com.alibaba.fastjson.JSONObject;
import com.zzyy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

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

        return itemService.list();
    }
}
