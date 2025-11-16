//package com.example.Monitoring_Communication_Microservice.components;
//
//import com.example.Monitoring_Communication_Microservice.dto.ChatMessageDTO;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class ChatWebSocketHandler extends TextWebSocketHandler {
//    // Map pentru a stoca sesiunile utilizatorilor (cheie = userId)
//    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
////    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
////    private static final String NOTIFICATIONS_QUEUE_PREFIX = "notifications_number:";
//    private static final String MESSAGE_QUEUE_PREFIX = "message_queue:";
//    // Ex query: ws://localhost:8082/chat?querykey=${querykey}
//
////    @Override
////    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
////
////        String querykey = session.getUri().getQuery().split("=")[1];
////
////        sessions.put(querykey, session);
////        System.out.println("User connected to session: " + querykey);
////    }
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String senderSessionName = session.getUri().getQuery().split("=")[1];
//        String senderId = senderSessionName.split("and")[0];
//        String receiverId = senderSessionName.split("and")[1];
//
//        // Parsează payload-ul JSON
//        String payload = message.getPayload();
//
//        String receiverSessionName = receiverId + "and" + senderId;
//
//        String messageText = "";
//        try {
//            messageText = extractValueFromPayload(payload, "messageText");
//
//            System.out.println("Parsed senderId: " + senderId);
//            System.out.println("Parsed receiverId: " + receiverId);
//            System.out.println("Parsed messageText: " + messageText);
//        } catch (Exception e) {
//            System.err.println("Error while parsing payload: " + e.getMessage());
//            e.printStackTrace();
//            session.sendMessage(new TextMessage("Error while parsing payload"));
//        }
//
//        try {
//
//            // Trimite mesajul doar utilizatorului destinatar
//            WebSocketSession recipientSession = sessions.get(receiverSessionName);
//            if (recipientSession != null && recipientSession.isOpen()) {
//                // Manually construct the JSON string
//                String outgoingMessage = String.format(
//                        "{\"senderId\":\"%s\",\"messageText\":\"%s\"}",
//                        senderId, messageText
//                );
//
//                recipientSession.sendMessage(new TextMessage(outgoingMessage));
//
//            } else {
//                // Queue notificari
////                String notificationKey = NOTIFICATIONS_QUEUE_PREFIX + receiverSessionName;
////
////                String notificationEntry = senderId + "and" + receiverId;
////                Set<Object> existingNotifications = redisTemplate.opsForSet().members(notificationKey);
//////                Set<String> existingNotifications = redisTemplate.opsForSet().members(notificationKey);
////
////                boolean notificationExists = false;
////
////                if (existingNotifications != null) {
////                    for (String entry : existingNotifications) {
////                        if (entry.startsWith(notificationEntry)) {
////                            // Dacă notificarea există, incrementează valoarea
////                            String[] parts = entry.split(":");
////                            int currentCount = Integer.parseInt(parts[1]);
////                            String updatedEntry = notificationEntry + ":" + (currentCount + 1);
////
////                            redisTemplate.opsForSet().remove(notificationKey, entry); // Șterge notificarea veche
////                            redisTemplate.opsForSet().add(notificationKey, updatedEntry); // Adaugă notificarea actualizată
////
////                            notificationExists = true;
////                            System.out.println("Incremented notification count for " + senderId + " to " + (currentCount + 1));
////                            break;
////                        }
////                    }
////                }
////
////                if (!notificationExists) {
////                    // Creează notificare nouă
////                    String newNotification = notificationEntry + ":1";
////                    redisTemplate.opsForSet().add(notificationKey, newNotification);
////                    System.out.println("Created new notification for " + senderId + " to " + receiverId);
////                }
////
//////                String newNotificationNumber = "1";
////                System.out.println("Updated notification count for receiverId " + receiverId);
////
////                if (redisTemplate.opsForHash().hasKey(notificationKey, senderId)) {
////                    // Incrementare notificare existentă
////                    redisTemplate.opsForSet().add()
//////                    redisTemplate.opsForHash().increment(notificationKey, numberOfNotifications, 1);
////                    System.out.println("Incremented notification count for " + receiverId + " sent by " + senderId);
////                } else {
////                    // Creează notificare nouă cu valoarea 1
////                    redisTemplate.opsForSet().add(notificationKey, 1);
////                    System.out.println("Created new notification for " + receiverId + " sent by " + senderId);
////                }
////                String newNotification = String.format(
////                        "{\"senderId\":\"%s\",\"noNotifications\":\"%s\"}",
////                        senderId, 1
////                );
//
//                // Queue mesaje
////                String status = "sent";
//
//                String outgoingMessage = String.format(
//                        "{\"senderId\":\"%s\",\"messageText\":\"%s\"}",
//                        senderId, messageText
//                );
//
//                String queueKey = MESSAGE_QUEUE_PREFIX + receiverSessionName;
//                redisTemplate.opsForList().leftPush(queueKey, objectMapper.writeValueAsString(outgoingMessage));
//
//                session.sendMessage(new TextMessage("User is not connected. Your message has been queued with status 'sent'."));
//            }
//        } catch (Exception e) {
//            System.err.println("Error while sending payload to receiver: " + e.getMessage());
//            e.printStackTrace();
//            session.sendMessage(new TextMessage("Error while sending payload to receiver."));
//        }
//    }
//
//@Override
//public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    String receiverSessionName = session.getUri().getQuery().split("=")[1];
//    String user1Key = receiverSessionName.split("and")[0];
//    String user2Key = receiverSessionName.split("and")[1];
//
//    sessions.put(receiverSessionName, session);
//    System.out.println("User connected to session: " + receiverSessionName);
//
//    // Verifică mesajele în queue-ul Redis
//    String queueKey = MESSAGE_QUEUE_PREFIX + receiverSessionName;
//
//    while (Boolean.TRUE.equals(redisTemplate.hasKey(queueKey)) &&
//            redisTemplate.opsForList().size(queueKey) > 0) {
//        String messageFromQueue = (String) redisTemplate.opsForList().rightPop(queueKey);
//
//        System.out.println("Message from queue: " + messageFromQueue);
//        // Deserializare mesaj
////            ChatMessageDTO chatMessageDTO = objectMapper.readValue(messageFromQueue, ChatMessageDTO.class);
//
//        // Actualizare status la "received"
////            chatMessageDTO.setStatus("received");
//
//        // Trimite mesajul utilizatorului destinatar
//        WebSocketSession recipientSession = sessions.get(receiverSessionName);
//        if (recipientSession != null && recipientSession.isOpen()) {
//            // Manually construct the JSON string
//            String outgoingMessage = String.format(
//                    "{\"senderId\":\"%s\",\"messageText\":\"%s\"}",
//                    user2Key, messageFromQueue
//            );
//
//            recipientSession.sendMessage(new TextMessage(outgoingMessage));
//
//            // Notifică sender-ul
//            String senderSessionName = user2Key + "and" + user1Key;
//            WebSocketSession senderSession = sessions.get(user2Key);
//            if (senderSession != null && senderSession.isOpen()) {
//                String notificationMessage = "Read messages";
//                senderSession.sendMessage(new TextMessage(notificationMessage));
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed (WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.values().remove(session);
//        System.out.println("Connection closed" + session.getId());
//    }
//
//    // Metodă pentru a extrage valori din payload
//    private String extractValueFromPayload (String payload, String key) throws Exception {
//        String keyPattern = "\"" + key + "\":\"";
//        int startIndex = payload.indexOf(keyPattern) + keyPattern.length();
//        if (startIndex < keyPattern.length()) {
//            throw new Exception("Key not found: " + key);
//        }
//        int endIndex = payload.indexOf("\"", startIndex);
//        if (endIndex == -1) {
//            throw new Exception("Value for key not properly terminated: " + key);
//        }
//        return payload.substring(startIndex, endIndex);
//    }
//}
//
//
