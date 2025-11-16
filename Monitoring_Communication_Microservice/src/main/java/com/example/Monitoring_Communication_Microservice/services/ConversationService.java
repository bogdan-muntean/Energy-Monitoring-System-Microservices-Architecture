//package com.example.Monitoring_Communication_Microservice.services;
//
//import com.example.Monitoring_Communication_Microservice.entities.Conversation;
//import com.example.Monitoring_Communication_Microservice.entities.User;
//import com.example.Monitoring_Communication_Microservice.repositories.ConversationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ConversationService {
//    private final ConversationRepository conversationRepository;
//
//    private final UserService userService;
//
//    @Autowired
//    public ConversationService(ConversationRepository conversationRepository, UserService userService) {
//        this.conversationRepository = conversationRepository;
//        this.userService = userService;
//    }
//
//    public List<Conversation> getAllConversations() {
//        return (List<Conversation>) conversationRepository.findAll();
//    }
//
//    public List<ArrayList<Object>> getAllConversationsByUserId(Integer userId) {
////        User principalUser = userService.getUser(Integer.parseInt(userId));
//
//        List<Conversation> conversationsAsParticipant1 = conversationRepository.findConversationByParticipant1Id(userId);
//        List<Conversation> conversationsAsParticipant2 = conversationRepository.findConversationByParticipant2Id(userId);
//        System.out.println("conversationsAsParticipant1: " + conversationsAsParticipant1.get(0).toString());
//        List<ArrayList<Object>> filteredConversations = new ArrayList<>();
//
//        for (Conversation conversation : conversationsAsParticipant1) {
//            Integer id = conversation.getParticipant2Id();
//            String username = userService.getUserUsername(id);
//            System.out.println("id: " + id + " username: " + username);
//
//            ArrayList<Object> toAdd = new ArrayList<>();
//            toAdd.add(conversation.getId());
//            toAdd.add(conversation.getParticipant2Id());
//            toAdd.add(username);
//            toAdd.add(conversation.getStatus());
//
//            System.out.println("toAdd 1: " + toAdd.toString());
//            filteredConversations.add(toAdd);
//        }
//
//        for (Conversation conversation : conversationsAsParticipant2) {
//            Integer id = conversation.getParticipant1Id();
//            String username = userService.getUserUsername(id);
//
//            ArrayList<Object> toAdd = new ArrayList<>();
//            toAdd.add(conversation.getId());
//            toAdd.add(conversation.getParticipant1Id());
//            toAdd.add(username);
//            toAdd.add(conversation.getStatus());
//
//            System.out.println("toAdd 2: " + toAdd.toString());
//            filteredConversations.add(toAdd);
//        }
//
////        filteredConversations.addAll(conversationsAsParticipant1);
////        filteredConversations.addAll(conversationsAsParticipant2);
//
//        return filteredConversations;
//    }
//}
