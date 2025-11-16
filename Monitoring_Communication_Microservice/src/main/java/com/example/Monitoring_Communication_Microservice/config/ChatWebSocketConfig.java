//package com.example.Monitoring_Communication_Microservice.config;
//
//import com.example.Monitoring_Communication_Microservice.components.ChatWebSocketHandler;
//import com.example.Monitoring_Communication_Microservice.components.WebSocketNotificationHandler;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.*;
//
//@Configuration
//@EnableWebSocket
//public class ChatWebSocketConfig implements WebSocketConfigurer {
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new ChatWebSocketHandler(), "/chat")
//                .setAllowedOrigins("*");
//    }
//}
//
////Folosit
////@Configuration
////@EnableWebSocketMessageBroker
////public class ChatWebSocketConfig implements WebSocketMessageBrokerConfigurer {
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry.addEndpoint("/ws/chat")
////                .setAllowedOrigins("*");
////    }
////
////    @Override
////    public void configureMessageBroker(MessageBrokerRegistry registry) {
////        registry.enableSimpleBroker("/chat");
////        registry.setApplicationDestinationPrefixes("/app");
////    }
////}
//
//
////@EnableWebSocketMessageBroker
////public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
////@Configuration
////@EnableWebSocket
////public class WebSocketConfig implements WebSocketConfigurer {
////    @Override
////    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////        registry.addHandler(new WebSocketNotificationHandler(), "/ws");
////    }
////}
//
////@Override
////public void configureMessageBroker(MessageBrokerRegistry registry) {
////    registry.enableSimpleBroker("/topic");
////    registry.setApplicationDestinationPrefixes("/app");
////}
//
//
//
//
////    @Override
////    public void configureMessageBroker(MessageBrokerRegistry config) {
////        config.enableSimpleBroker("/topic"); // Broker pentru canale de mesaje
////        config.setApplicationDestinationPrefixes("/app"); // Prefix pentru endpoint-uri
////    }
////
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry.addEndpoint("/ws")
////                .setAllowedOriginPatterns("http://localhost:3000") // Specifică originile permise
////                .withSockJS(); // Folosește SockJS pentru fallback
////    }
//
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS(); // Endpoint pentru WebSocket
////    }
////    private final WebSocketNotificationHandler notificationHandler;
////
////    public WebSocketConfig(WebSocketNotificationHandler notificationHandler) {
////        this.notificationHandler = notificationHandler;
////    }
////
////    @Override
////    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////        registry.addHandler(notificationHandler, "/ws/notifications")
////                .setAllowedOrigins("*");
////    }
//
