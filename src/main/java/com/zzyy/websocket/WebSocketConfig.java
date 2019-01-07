package com.zzyy.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.AbstractWebSocketMessage;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/7 14:32
 * @Description:开启WebSocket支持
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
