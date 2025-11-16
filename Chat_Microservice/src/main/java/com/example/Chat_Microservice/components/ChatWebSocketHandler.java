//package com.example.Chat_Microservice.components;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class ChatWebSocketHandler implements WebSocketHandler {
//    // Map pentru a stoca sesiunile utilizatorilor (cheie = userId)
//    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
////    @Autowired
//    private final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//
//    //    private static final String NOTIFICATIONS_QUEUE_PREFIX = "notifications_number:";
//    private static final String MESSAGE_QUEUE_PREFIX = "message_queue:";
//
//    public ChatWebSocketHandler() {
////        this.redisTemplate = redisTemplate;
//    }
//
//    // Ex query: ws://localhost:8083/chat?querykey=${querykey}
//    // redis IOPqwe10
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.remove(session);
//
//        //Anuntam interlocutorul ca devenim offline.
//        String senderSessionName = session.getUri().getQuery().split("=")[1];
//        String senderId = senderSessionName.split("and")[0];
//        String receiverId = senderSessionName.split("and")[1];
//
//        String receiverSessionName = receiverId + "and" + senderId;
//
//        WebSocketSession receiverSession = sessions.get(receiverSessionName);
//        if (receiverSession != null && receiverSession.isOpen()) {
//            // Manually construct the JSON string
//            String connectionStatus = "offline";
//            String outgoingMessage = String.format(
//                    "{\"connectionStatus\":\"%s\"}",
//                    connectionStatus
//            );
//            receiverSession.sendMessage(new TextMessage(outgoingMessage));
//
////            sessions.values().remove(session);
//            System.out.println("Connection closed" + session.getId());
//        }
//    }
//
//    public void handleTextMessage (WebSocketSession session, TextMessage message) throws Exception {
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
//        } catch (Exception e) {
//            System.err.println("Error while parsing payload: " + e.getMessage());
//            e.printStackTrace();
//            session.sendMessage(new TextMessage("Error while parsing payload"));
//        }
//
//        try {
//            // Trimite mesajul doar utilizatorului destinatar
//            WebSocketSession recipientSession = sessions.get(receiverSessionName);
//            if (recipientSession != null && recipientSession.isOpen()) {
//                // Manually construct the JSON string
//                String outgoingMessage = String.format(
//                        "{\"senderId\":\"%s\",\"message\":\"%s\",\"messageType\":\"%s\"}",
//                        senderId, messageText, "messageText"
//                );
//
//                recipientSession.sendMessage(new TextMessage(outgoingMessage));
//
//            } else {
//                // Daca utilizatorul nu este conectat, adaugam mesajul in redis, asignat unei key "destinatar+ and +expeditor", ex "2and1"
//                // Queue mesaje
////                    String messageStatus = "sent";
//
//                // Instiintam expeditorul ca destinatarul nu este online si mesajul este doar trimis
//                throw (new RuntimeException("sent"));
////                String outgoingMessage = String.format(
////                        "{\"messageText\":\"%s\"}",
////                        messageStatus
////                );
////
////                session.sendMessage(new TextMessage(outgoingMessage));
//            }
//        } catch (Exception e) {
//            System.err.println("Error while sending payload to receiver: " + e.getMessage());
//            e.printStackTrace();
//            session.sendMessage(new TextMessage("Error while sending payload to receiver."));
//        }
//    }
//
////        @Override
////        public void handleTextMessage (WebSocketSession session, TextMessage message) throws Exception {
////            String currentSessionName = session.getUri().getQuery().split("=")[1];
////            String firstId = currentSessionName.split("and")[0];
////            String secondId = currentSessionName.split("and")[1];
////
////            // Parsează payload-ul JSON
////            String payload = message.getPayload();
////
////            String messageType = extractValueFromPayload(payload, "messageType");
////
////            if(messageType == null) {
////                throw new Exception("Invalid message type");
////            } else if(messageType == "messageText"){
////                String senderId = extractValueFromPayload(payload, "senderId");
//////                String message = extractValueFromPayload(payload, "message");
////                if(senderId == secondId) {
////                    //mesaj primit - nu facem nimic altceva
////
////                } else {
////                    //trimit mesaj in sesiunea receiver-ului
////                    String receiverSessionName = secondId + "and" + firstId;
////                    WebSocketSession recipientSession = sessions.get(receiverSessionName);
////                    if (recipientSession != null && recipientSession.isOpen()) {
////                        // Manually construct the JSON string
////                        String outgoingMessage = String.format(
////                                "{\"senderId\":\"%s\",\"message\":\"%s\",\"messageType\":\"%s\"}",
////                                senderId, message, "messageText"
////                        );
////
////                        recipientSession.sendMessage(new TextMessage(outgoingMessage));
////
////                    }
////                }
////            }else if(messageType == "messageStatus"){
////                //primesc instiintare de la receiver ca s-a conectat la sesiune
////            }
//
//
////            String messageText = "";
////            try {
////                messageText = extractValueFromPayload(payload, "messageText");
////            } catch (Exception e) {
////                System.err.println("Error while parsing payload: " + e.getMessage());
////                e.printStackTrace();
////                session.sendMessage(new TextMessage("Error while parsing payload"));
////            }
//
////            try {
////                // Trimite mesajul doar utilizatorului destinatar
////                WebSocketSession recipientSession = sessions.get(receiverSessionName);
////                if (recipientSession != null && recipientSession.isOpen()) {
////                    // Manually construct the JSON string
////                    String outgoingMessage = String.format(
////                            "{\"senderId\":\"%s\",\"message\":\"%s\",\"messageType\":\"%s\"}",
////                            senderId, message, "messageText"
////                    );
////
////                    recipientSession.sendMessage(new TextMessage(outgoingMessage));
////
////                } else {
////                    // Daca utilizatorul nu este conectat, adaugam mesajul in redis, asignat unei key "destinatar+ and +expeditor", ex "2and1"
////                    // Queue mesaje
//////                    String messageStatus = "sent";
////
////                    // Instiintam expeditorul ca destinatarul nu este online si mesajul este doar trimis
////                    throw (new RuntimeException("sent"));
//////                String outgoingMessage = String.format(
//////                        "{\"messageText\":\"%s\"}",
//////                        messageStatus
//////                );
//////
//////                session.sendMessage(new TextMessage(outgoingMessage));
////                }
////            } catch (Exception e) {
////                System.err.println("Error while sending payload to receiver: " + e.getMessage());
////                e.printStackTrace();
////                session.sendMessage(new TextMessage("Error while sending payload to receiver."));
////            }
////        }
//
//        @Override
//        public void afterConnectionEstablished (WebSocketSession session) throws Exception {
////        RedisTemplate currentRedisTemplate = new RedisTemplate();
//            String receiverSessionName = session.getUri().getQuery().split("=")[1];
//            String user1Key = receiverSessionName.split("and")[0];
//            String user2Key = receiverSessionName.split("and")[1];
//
//            String senderSessionName = user2Key + "and" + user1Key;
//
//            sessions.put(receiverSessionName, session);
//            System.out.println("User connected to session: " + receiverSessionName);
//
//            // Verifică daca sunt mesaje în queue-ul Redis
//            String redisQueueKey = MESSAGE_QUEUE_PREFIX + receiverSessionName;
//
//            while (Boolean.TRUE.equals(redisTemplate.hasKey(redisQueueKey)) &&
//                    redisTemplate.opsForList().size(redisQueueKey) > 0) {
//
//                String messageRedisFromQueue = (String) redisTemplate.opsForList().rightPop(redisQueueKey);
//
//                System.out.println("Message from queue: " + messageRedisFromQueue);
//                // Deserializare mesaj
//                //            ChatMessageDTO chatMessageDTO = objectMapper.readValue(messageFromQueue, ChatMessageDTO.class);
//
//                // Actualizare status la "received"
//                //            chatMessageDTO.setStatus("received");
//
//                // Trimite mesajul utilizatorului destinatar, cel ce era in asteptare in redis (mesaj propriului session)
//                String outgoingMessage = String.format(
//                        "{\"senderId\":\"%s\",\"message\":\"%s\",\"messageType\":\"%s\"}",
//                        user2Key, messageRedisFromQueue, "messageText"
//                );
//
//                session.sendMessage(new TextMessage(outgoingMessage));
//            }
//
//            // Notifică sender-ul ca receiver-ul a primit mesajele din redis + Poate fi catalogat si ca "Interlocutorul s-a conectat la sesiune".
//            String messageStatus = "seen";
//            WebSocketSession senderSession = sessions.get(senderSessionName);
//            if (senderSession != null && senderSession.isOpen()) {
//                // Manually construct the JSON string
//                String outgoingMessage = String.format(
//                        "{\"senderId\":\"%s\",\"message\":\"%s\",\"messageType\":\"%s\"}",
//                        user2Key, messageStatus, "messageStatus"
//                );
//
//                senderSession.sendMessage(new TextMessage(outgoingMessage));
//            }
//        }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//
//    }
//
//    // Metodă pentru a extrage valori din payload
//        private String extractValueFromPayload (String payload, String key) throws Exception {
//            String keyPattern = "\"" + key + "\":\"";
//            int startIndex = payload.indexOf(keyPattern) + keyPattern.length();
//            if (startIndex < keyPattern.length()) {
//                throw new Exception("Key not found: " + key);
//            }
//            int endIndex = payload.indexOf("\"", startIndex);
//            if (endIndex == -1) {
//                throw new Exception("Value for key not properly terminated: " + key);
//            }
//            return payload.substring(startIndex, endIndex);
//        }


















//    private String extractValueFromPayload(String payload, String key) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode node = objectMapper.readTree(payload);
//        return node.get(key).asText();
//    }

//}

// Queue notificari
//                redisTemplate.opsForValue().set(key, value);
//Object result = redisTemplate.opsForValue().get(key);

//                String notificationKey = NOTIFICATIONS_QUEUE_PREFIX + receiverSessionName;
//
//                String notificationEntry = senderId + "and" + receiverId;
//                Set<Object> existingNotifications = redisTemplate.opsForSet().members(notificationKey);
////                Set<String> existingNotifications = redisTemplate.opsForSet().members(notificationKey);
//
//                boolean notificationExists = false;
//
//                if (existingNotifications != null) {
//                    for (String entry : existingNotifications) {
//                        if (entry.startsWith(notificationEntry)) {
//                            // Dacă notificarea există, incrementează valoarea
//                            String[] parts = entry.split(":");
//                            int currentCount = Integer.parseInt(parts[1]);
//                            String updatedEntry = notificationEntry + ":" + (currentCount + 1);
//
//                            redisTemplate.opsForSet().remove(notificationKey, entry); // Șterge notificarea veche
//                            redisTemplate.opsForSet().add(notificationKey, updatedEntry); // Adaugă notificarea actualizată
//
//                            notificationExists = true;
//                            System.out.println("Incremented notification count for " + senderId + " to " + (currentCount + 1));
//                            break;
//                        }
//                    }
//                }
//
//                if (!notificationExists) {
//                    // Creează notificare nouă
//                    String newNotification = notificationEntry + ":1";
//                    redisTemplate.opsForSet().add(notificationKey, newNotification);
//                    System.out.println("Created new notification for " + senderId + " to " + receiverId);
//                }
//
////                String newNotificationNumber = "1";
//                System.out.println("Updated notification count for receiverId " + receiverId);
//
//                if (redisTemplate.opsForHash().hasKey(notificationKey, senderId)) {
//                    // Incrementare notificare existentă
//                    redisTemplate.opsForSet().add()
////                    redisTemplate.opsForHash().increment(notificationKey, numberOfNotifications, 1);
//                    System.out.println("Incremented notification count for " + receiverId + " sent by " + senderId);
//                } else {
//                    // Creează notificare nouă cu valoarea 1
//                    redisTemplate.opsForSet().add(notificationKey, 1);
//                    System.out.println("Created new notification for " + receiverId + " sent by " + senderId);
//                }
//                String newNotification = String.format(
//                        "{\"senderId\":\"%s\",\"noNotifications\":\"%s\"}",
//                        senderId, 1
//                );