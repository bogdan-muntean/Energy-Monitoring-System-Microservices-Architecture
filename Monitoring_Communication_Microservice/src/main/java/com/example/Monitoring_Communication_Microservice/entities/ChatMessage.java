//package com.example.Monitoring_Communication_Microservice.entities;
//
//import jakarta.persistence.*;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Entity
//@Table(name = "messages")
//public class ChatMessage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "conversation_id", nullable = false)
//    private Integer conversationId;
//
//    @Column(name = "sender_id", nullable = false)
//    private Integer senderId;
//
//    @Column(name = "receiver_id", nullable = false)
//    private Integer receiverId;
//
//    @Column(name = "message_text", nullable = false, columnDefinition = "TEXT")
//    private String messageText;
//
//    @Column(name = "is_read", nullable = false)
//    private Boolean isRead = false;
//
//    @Column(name = "sent_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime sentAt = LocalDateTime.now();
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Integer getConversationId() {
//        return conversationId;
//    }
//
//    public void setConversationId(Integer conversationId) {
//        this.conversationId = conversationId;
//    }
//
//    public Integer getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(Integer senderId) {
//        this.senderId = senderId;
//    }
//
//    public Integer getReceiverId() {
//        return receiverId;
//    }
//
//    public void setReceiverId(Integer receiverId) {
//        this.receiverId = receiverId;
//    }
//
//    public String getMessageText() {
//        return messageText;
//    }
//
//    public void setMessageText(String messageText) {
//        this.messageText = messageText;
//    }
//
//    public Boolean getIsRead() {
//        return isRead;
//    }
//
//    public void setIsRead(Boolean isRead) {
//        this.isRead = isRead;
//    }
//
//    public LocalDateTime getSentAt() {
//        return sentAt;
//    }
//
//    public void setSentAt(LocalDateTime sentAt) {
//        this.sentAt = sentAt;
//    }
//
//    // toString method
//    @Override
//    public String toString() {
//        return "ChatMessage{" +
//                "conversationId=" + conversationId +
//                ", senderId=" + senderId +
//                ", receiverId=" + receiverId +
//                ", messageText='" + messageText + '\'' +
//                ", isRead=" + isRead +
//                ", sentAt=" + sentAt +
//                '}';
//    }
//
//    // equals and hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ChatMessage that = (ChatMessage) o;
//        return isRead == that.isRead &&
//                Objects.equals(conversationId, that.conversationId) &&
//                Objects.equals(senderId, that.senderId) &&
//                Objects.equals(receiverId, that.receiverId) &&
//                Objects.equals(messageText, that.messageText) &&
//                Objects.equals(sentAt, that.sentAt);
//    }
//}
