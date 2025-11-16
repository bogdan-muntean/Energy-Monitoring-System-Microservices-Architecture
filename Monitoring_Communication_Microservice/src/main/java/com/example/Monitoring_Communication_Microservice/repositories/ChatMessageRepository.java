//package com.example.Monitoring_Communication_Microservice.repositories;
//
//import com.example.Monitoring_Communication_Microservice.entities.ChatMessage;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
//    List<ChatMessage> findByReceiverIdAndIsReadFalse(Long receiverId);
//}
//
////public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
////    @Query("SELECT m FROM ChatMessage m WHERE m.receiverId = :receiverId AND m.isRead = false")
////    List<ChatMessage> findUnreadMessagesByReceiverId(@Param("receiverId") Long receiverId);
////}
