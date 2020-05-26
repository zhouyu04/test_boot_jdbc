package com.zzyy.service.impl;

import com.zzyy.entity.WxVerifyTicket;
import com.zzyy.mapper.WxMapper;
import com.zzyy.service.WxService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/26 19:31
 * @Description:
 */
@Service
public class WxServiceImpl implements WxService {


    @Resource
    WxMapper wxMapper;


    @Override
    public void saveVerifyTicket(String dataFromRequst) throws DocumentException {
        Document document = DocumentHelper.parseText(dataFromRequst);
        Element rootElement = document.getRootElement();

        Element appId = rootElement.element("AppId");
        String appIdStringValue = appId.getStringValue();

        Element infoType = rootElement.element("InfoType");
        String type = infoType.getStringValue();

        Element componentVerifyTicket = rootElement.element("ComponentVerifyTicket");
        String verifyTicket = componentVerifyTicket.getStringValue();

        //查询是否已经存在
        WxVerifyTicket exist = wxMapper.findByKey(appIdStringValue, type);

        if (exist != null) {
            exist.setTicket(verifyTicket);
            wxMapper.updateVerifyTicket(exist);
        } else {
            WxVerifyTicket vt = new WxVerifyTicket();

            vt.setAppId(appIdStringValue);
            vt.setInfoType(type);
            vt.setTicket(verifyTicket);

            wxMapper.saveVerifyTicket(vt);
        }
    }
}
