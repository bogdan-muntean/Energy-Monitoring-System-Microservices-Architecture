//package com.example.Monitoring_Communication_Microservice.controllers;
//
//import com.example.Monitoring_Communication_Microservice.entities.Conversation;
//import com.example.Monitoring_Communication_Microservice.services.ConversationService;
//import com.example.Monitoring_Communication_Microservice.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/conversations")
//@CrossOrigin
//public class ConversationController {
//    @Autowired
//    private ConversationService conversationService;
//
//    @GetMapping("/get-all")
//    public ResponseEntity<List<Conversation>> getAllConversations() {
//        List<Conversation> conversations = conversationService.getAllConversations();
//        return ResponseEntity.ok(conversations);
//    }
//
//    @GetMapping("/get-all-for/{id}")
//    public ResponseEntity<List<ArrayList<Object>>> getAllConversationsForUser(@PathVariable Integer id) {
////        String userId = String.valueOf(id);
//        System.out.println(id);
//        List<ArrayList<Object>> conversations = conversationService.getAllConversationsByUserId(id);
//        System.out.println(conversations);
//        return ResponseEntity.ok(conversations);
//    }
//
//}
