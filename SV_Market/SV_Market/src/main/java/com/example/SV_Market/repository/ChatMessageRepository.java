package com.example.SV_Market.repository;

import com.example.SV_Market.entity.ChatMessage;
import com.example.SV_Market.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    @Query("SELECT cm FROM ChatMessage cm WHERE cm.chatId = :chatId")
    List<ChatMessage> findByChatId(@Param("chatId") String chatId);
//    @Query("SELECT u FROM chatroom u WHERE u.sender_id = :senderId and u.receiver_id = :receiverId")
//    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
