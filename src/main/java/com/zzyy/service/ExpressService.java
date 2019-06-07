package com.zzyy.service;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.entity.Express;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ExpressService {
    void add(Express express);

    void delete(Long id);

    List<Express> find(Map<String,Object> map);

    long findCount(Map<String,Object> map);
}
