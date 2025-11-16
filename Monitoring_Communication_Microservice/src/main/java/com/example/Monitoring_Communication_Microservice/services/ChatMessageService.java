//package com.example.Monitoring_Communication_Microservice.services;
//import com.example.Monitoring_Communication_Microservice.entities.ChatMessage;
//import com.example.Monitoring_Communication_Microservice.repositories.ChatMessageRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ChatMessageService {
//    private final ChatMessageRepository messageRepository;
//
//    public ChatMessageService(ChatMessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }
//
//    public void saveMessage(ChatMessage message) {
//        // Salvează mesajul în baza de date
//        messageRepository.save(message);
//    }
//
//    public List<ChatMessage> getUnreadMessages(Long userId) {
//        return messageRepository.findByReceiverIdAndIsReadFalse(userId);
//    }
//}
//
////package com.example.Monitoring_Communication_Microservice.services;
////
////import com.example.Monitoring_Communication_Microservice.components.WebSocketNotificationHandler;
////import org.springframework.stereotype.Service;
////
////@Service
////public class WebSocketNotificationService {
////
////    private final WebSocketNotificationHandler notificationHandler;
////
////    public WebSocketNotificationService(WebSocketNotificationHandler notificationHandler) {
////        this.notificationHandler = notificationHandler;
////    }
////
////    public void sendNotification(Long deviceId, String message) {
////        String notification = "Device ID: " + deviceId + " - " + message;
////
////        notificationHandler.handleTextMessage();
////    }
////}