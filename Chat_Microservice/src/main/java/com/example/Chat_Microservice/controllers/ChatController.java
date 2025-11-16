package com.example.Chat_Microservice.controllers;

import com.example.Chat_Microservice.entities.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    // Constructor pentru injectarea SimpMessagingTemplate
    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

//    @MessageMapping("/chat")
//    @SendToUser("/queue/messages") // Trimite mesajul doar utilizatorului
//    public ChatMessage handleChatMessage(ChatMessage message, Principal principal) {
//        System.out.println("Received message: " + message);
//        return message;
//    }

    @MessageMapping("/chat")
    public void handleChatMessage(ChatMessage message, Principal principal) {
//        System.out.println("Received message from: " + principal.getName() + " - " + message);

        // Direcționează mesajul către utilizatorul dorit
        String sessionName = message.getReceiverId() + "and" + message.getSenderId();
        System.out.println("Send message to: " + sessionName);

        String destination = "/queue/messages/" + sessionName;
        messagingTemplate.convertAndSend(destination, message);
    }
}

//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public ChatController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    // Trimitere mesaj public către toți utilizatorii abonați la /topic/public
////    @MessageMapping("/sendPublic")
////    public void sendPublicMessage(String message) {
////        messagingTemplate.convertAndSend("/topic/public", message);
////    }
//
//    // Trimitere mesaj privat către un utilizator specific
//    @MessageMapping("/sendPrivate")
//    public void sendPrivateMessage(PrivateMessage privateMessage) {
//        String destination = "/queue/private-" + privateMessage.getRecipientId();
//        messagingTemplate.convertAndSend(destination, privateMessage.getContent());
//    }
//
//    // Clasa pentru mesajele private
//    public static class PrivateMessage {
//        private String recipientId;
//        private String content;
//
//        // Getteri și setteri
//        public String getRecipientId() { return recipientId; }
//        public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
//
//        public String getContent() { return content; }
//        public void setContent(String content) { this.content = content; }
//    }
//}