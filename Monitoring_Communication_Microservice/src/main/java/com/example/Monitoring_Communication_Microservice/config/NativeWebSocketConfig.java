//package com.example.Monitoring_Communication_Microservice.config;
//
//import com.example.Monitoring_Communication_Microservice.components.MyWebSocketHandler;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//@Configuration
//public class NativeWebSocketConfig implements WebSocketConfigurer {
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new MyWebSocketHandler(), "/ws")
//                .setAllowedOrigins("http://localhost:3000") // Permite frontend-ul
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
//    }
//}
