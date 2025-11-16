//package com.example.Monitoring_Communication_Microservice.controllers;
//
//import com.example.Monitoring_Communication_Microservice.entities.ChatMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatWebSocketController {
//    private final SimpMessagingTemplate messagingTemplate;
////    private final MessageService messageService = new MessageService();
////    private final ConcurrentHashMap<String, Queue<String>> userMessageQueues = new ConcurrentHashMap<>();
//
//    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @MessageMapping("/chat/send")
//    public void sendMessage(@Payload ChatMessage message) {
//        // Save message to the database
//        messagingTemplate.convertAndSendToUser(message.getReceiverId().toString(), "/queue/messages", message);
//    }
//
////    @MessageMapping("/chat/typing")
////    public void typingNotification(@Payload TypingNotification typing) {
////        messagingTemplate.convertAndSendToUser(typing.getReceiverId().toString(), "/queue/typing", typing);
////    }
////
////    @MessageMapping("/chat/read")
////    public void messageRead(@Payload ReadNotification read) {
////        // Update message status in database
////        messagingTemplate.convertAndSendToUser(read.getSenderId().toString(), "/queue/read", read);
////    }
//}
//
//
////    @MessageMapping ("/send")
////    @SendTo("/alert/{userId}")
////    public String handleTextMessage(String message) {
////        return message;
////    }