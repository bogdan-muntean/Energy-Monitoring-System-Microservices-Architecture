//package com.example.Monitoring_Communication_Microservice.components;
//
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//public class MyWebSocketHandler extends TextWebSocketHandler {
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // Prelucrează mesajele primite
//        System.out.println("Message received: " + message.getPayload());
//
//        // Trimite un răspuns
//        session.sendMessage(new TextMessage("Server received: " + message.getPayload()));
//    }
//}
