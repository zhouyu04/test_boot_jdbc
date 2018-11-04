package com.zzyy.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    void addItem();

    JSONObject list();
}
