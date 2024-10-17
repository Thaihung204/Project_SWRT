package com.example.SV_Market.repository;


import com.example.SV_Market.entity.Message;
import com.example.SV_Market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(User sender, User receiver, User receiver2, User sender2);
}


