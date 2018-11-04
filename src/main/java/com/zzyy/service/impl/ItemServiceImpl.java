package com.zzyy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzyy.entity.TBItem;
import com.zzyy.mapper.TBItemMapper;
import com.zzyy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    TBItemMapper tbItemMapper;

    @Override
    public void addItem() {
        TBItem tbItem = new TBItem();

        tbItem.setId(1L);
        tbItem.setTitle("苹果X max");
        tbItem.setPrice(8000L);
        tbItem.setNum(100);
        tbItem.setCid(12358L);
        tbItem.setStatus(1l);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());

        tbItemMapper.insert(tbItem);
    }

    @Override
    public JSONObject list() {
        JSONObject object = new JSONObject();

        try {

            List<TBItem> tbItems = tbItemMapper.selectAll();

            String result = JSONArray.toJSONString(tbItems);
            object.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
}
