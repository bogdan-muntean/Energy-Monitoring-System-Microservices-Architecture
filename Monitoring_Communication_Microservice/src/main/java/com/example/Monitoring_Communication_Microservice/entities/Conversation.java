//package com.example.Monitoring_Communication_Microservice.entities;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "conversations")
//public class Conversation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "participant1_id", nullable = false)
//    private Integer participant1Id;
//
//    @Column(name = "participant2_id", nullable = false)
//    private Integer participant2Id;
//
//    @Column(name = "status", nullable = false, length = 20)
//    private String status = "active";
//
//    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime createdAt = LocalDateTime.now();
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
//    public Integer getParticipant1Id() {
//        return participant1Id;
//    }
//
//    public void setParticipant1Id(Integer participant1Id) {
//        this.participant1Id = participant1Id;
//    }
//
//    public Integer getParticipant2Id() {
//        return participant2Id;
//    }
//
//    public void setParticipant2Id(Integer participant2Id) {
//        this.participant2Id = participant2Id;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    @Override
//    public String toString() {
//        return "Conversation{" +
//                "id=" + id +
//                ", participant1Id=" + participant1Id +
//                ", participant2Id=" + participant2Id +
//                ", status='" + status + '\'' +
//                ", createdAt=" + createdAt +
//                '}';
//    }
//}
