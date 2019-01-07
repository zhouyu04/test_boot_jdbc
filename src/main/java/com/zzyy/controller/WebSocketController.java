package com.zzyy.controller;

import com.zzyy.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/7 16:37
 * @Description:
 */
@Controller
@RequestMapping("/socket")
public class WebSocketController {

    private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    WebSocketServer webSocketServer;//websocket服务


    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public void getMessage(HttpServletRequest request) {

        String message = request.getParameter("message");

        try {
            webSocketServer.sendMessage(message);
        } catch (IOException e) {
            logger.error("IO 异常", e);
        }
    }

    /**
     * 功能描述: websocket发送消息
     *
     * @auther: zhouyu
     * @date: 2019/1/7 17:00
     */
    @RequestMapping(value = "/sendMessage/{sid}", method = RequestMethod.GET)
    public void sendMessage(@PathVariable String sid) {

        String message = "测试websocket";
        try {
            webSocketServer.sendInfo(message,sid);
        } catch (IOException e) {
            logger.error("IO 异常",e);
        }
    }
}
