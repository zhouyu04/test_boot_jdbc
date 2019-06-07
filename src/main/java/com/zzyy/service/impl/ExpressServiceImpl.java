package com.zzyy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.entity.Express;
import com.zzyy.mapper.UserMapper;
import com.zzyy.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(Express express) {
        userMapper.addExpress(express);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteExpress(id);
    }

    @Override
    public List<Express> find(Map<String,Object> map) {

        List<Express> expressList = userMapper.findExpress(10086L);

        return expressList;
    }

    @Override
    public long findCount(Map<String,Object> map) {

        return userMapper.findExpressCount(10086L);
    }
}
