package com.zzyy.mapper;

import com.zzyy.entity.WxVerifyTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/26 19:33
 * @Description:
 */
@Mapper
public interface WxMapper {

    void saveVerifyTicket(WxVerifyTicket vt);

    WxVerifyTicket findByKey(@Param("appId") String appId, @Param("infoType") String infoType);

    void updateVerifyTicket(WxVerifyTicket exist);

    WxVerifyTicket findByParams(Map<String, Object> params);

    WxVerifyTicket findAccessToken(@Param("appId") String appid, @Param("authAppId") String authorizer_appid);

    void updateToken(WxVerifyTicket wt);

    void deleteBydbid(String dbid);
}
