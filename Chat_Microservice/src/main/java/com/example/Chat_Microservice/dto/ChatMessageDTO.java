package com.example.Chat_Microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChatMessageDTO {
    @JsonProperty("senderId")
    private String senderId;

    @JsonProperty("receiverId")
    private String receiverId;

    @JsonProperty("messageText")
    private String messageText;

    public ChatMessageDTO() {}

    public ChatMessageDTO(String senderId, String receiverId, String messageText) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
    }

    // Getters and Setters
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    // toString method
    @Override
    public String toString() {
        return "ChatMessage{" +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", messageText='" + messageText + '\'' +
                '}';
    }
}

//import jakarta.persistence.Column;
//
//import java.awt.*;
//
//public class ChatMessageDTO {
//    private String senderId;
//    private String receiverId;
//    private String messageText;
//    private MessageType messageType;
//
//    public enum MessageType {
//        CHAT, TYPING, READ
//    }
//
//    public ChatMessageDTO() {}
//
//    public String getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(String senderId) {
//        this.senderId = senderId;
//    }
//
//    public String getReceiverId() {
//        return receiverId;
//    }
//
//    public void setReceiverId(String receiverId) {
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
//    public MessageType getMessageType() {
//        return messageType;
//    }
//
//    public void setMessageType(MessageType messageType) {
//        this.messageType = messageType;
//    }
//}
