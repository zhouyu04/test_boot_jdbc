package com.zzyy.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/7 14:42
 * @Description:
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid = "";

    /**
     * 功能描述: 开启连接
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:48
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);
        //加入set中 在线数加1
        addOnlineCount();
        logger.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 功能描述: 关闭连接
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:49
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        //从set中删除，在线数减1
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 功能描述: 接收消息
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:49
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口" + sid + "的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.error("IO 异常",e);
            }
        }
    }

    /**
     * 功能描述: 错误
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:49
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
    }

    /**
     * 功能描述: 发送消息
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:49
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 功能描述: 群发自定义消息
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:50
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        logger.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                logger.error("IO 异常",e);
                continue;
            }
        }
    }


    /**
     * 功能描述: 获取当前连接人数
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:50
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 功能描述: 添加统计人数+1
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:50
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 功能描述: 减少统计人数-1
     *
     * @auther: zhouyu
     * @date: 2019/1/7 14:50
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
