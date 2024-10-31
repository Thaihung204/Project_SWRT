package com.example.SV_Market.repository;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    @Query("SELECT c FROM ChatRoom c WHERE c.senderId = :senderId AND c.receiverId = :receiverId")
    Optional<ChatRoom> findBySenderIdAndReceiverId(@Param("senderId") String senderId, @Param("receiverId") String receiverId);
}
