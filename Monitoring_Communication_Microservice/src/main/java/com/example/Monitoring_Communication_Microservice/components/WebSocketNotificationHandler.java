package com.example.Monitoring_Communication_Microservice.components;

//import com.example.Monitoring_Communication_Microservice.entities.ChatMessage;
//import com.example.Monitoring_Communication_Microservice.services.ChatMessageService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WebSocketNotificationHandler extends TextWebSocketHandler {

    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
//    private final ChatMessageService messageService = new ChatMessageService();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

//        Long userId = extractUserIdFromSession(session); // Extragere ID utilizator din sesiune
//        List<ChatMessage> unreadMessages = messageService.getUnreadMessages(userId);
//
//        // Trimite notificări pentru fiecare mesaj necitit
//        for (ChatMessage message : unreadMessages) {
//            messagingTemplate.convertAndSend("/alert/" + userId, message);
//        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession webSocketSession : sessions) {
            if(!webSocketSession.getId().equals(session.getId())) {
                webSocketSession.sendMessage(message);
                  System.out.println("Message sent to websocket, message: "+ message);
            }
        }
    }

//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
//        for (WebSocketSession webSocketSession : sessions) {
//            if (webSocketSession.isOpen() && !webSocketSession.getId().equals(session.getId())) {
//                webSocketSession.sendMessage(message);
//                System.out.println("Message sent to websocket, message: "+ message);
//            }
//        }
//    }
}
