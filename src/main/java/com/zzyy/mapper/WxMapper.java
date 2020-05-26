package com.zzyy.mapper;

import com.zzyy.entity.WxVerifyTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
