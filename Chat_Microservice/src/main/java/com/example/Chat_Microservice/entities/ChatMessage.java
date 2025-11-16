package com.example.Chat_Microservice.entities;

public class ChatMessage {
    private String senderId;
    private String receiverId;
    private String message;
    private String messageType;

    // Constructor implicit
    public ChatMessage() {
    }

    // Constructor cu parametri
    public ChatMessage(String senderId, String receiverId, String message, String messageType) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.messageType = messageType;
    }

    // Getter pentru senderId
    public String getSenderId() {
        return senderId;
    }

    // Setter pentru senderId
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    // Getter pentru message
    public String getMessage() {
        return message;
    }

    // Setter pentru message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter pentru messageType
    public String getMessageType() {
        return messageType;
    }

    // Setter pentru messageType
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    // Metodă toString pentru debugging
    @Override
    public String toString() {
        return "ChatMessage{" +
                "senderId='" + senderId + '\'' +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}