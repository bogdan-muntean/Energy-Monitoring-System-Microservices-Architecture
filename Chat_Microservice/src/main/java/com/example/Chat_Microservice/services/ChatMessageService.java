//package com.example.Chat_Microservice.services;
//
//import com.example.Chat_Microservice.entities.ChatMessage;
//import com.example.Chat_Microservice.repositories.ChatMessageRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ChatMessageService {
//    private final ChatMessageRepository chatMessageRepository;
//
//    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
//        this.chatMessageRepository = chatMessageRepository;
//    }
//
//    public ChatMessage saveMessage(ChatMessage chatMessage) {
//        System.out.println("----------------: "+ chatMessage.getMessageText());
//        System.out.println("----------------: "+ chatMessage.getIsRead());
//        System.out.println("----------------: "+ chatMessage.getReceiverId());
//        System.out.println("----------------: "+ chatMessage.getSenderId());
//        System.out.println("----------------: "+ chatMessage.getSentAt());
//
//        try {
//            ChatMessage responseSavedMessage = chatMessageRepository.save(chatMessage);
//            return responseSavedMessage;
//        } catch (Exception e) {
//            System.out.println("Error when saving message: " + e);
//            throw e;
//        }
//    }
//
//    public List<ChatMessage> getChatMessagesHistory(String user1, String user2) {
//        return chatMessageRepository.findChatHistory(user1, user2);
//    }
//
//    public List<ChatMessage> getAllChatMessages() {
//        return chatMessageRepository.findAll();
//    }
//}
