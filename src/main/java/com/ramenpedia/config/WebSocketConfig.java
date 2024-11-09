package com.ramenpedia.config;

import com.ramenpedia.handler.QueueWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private QueueWsHandler queueWsHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler(), "/queue-ws").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler handler() {
        return queueWsHandler;
    }
}
