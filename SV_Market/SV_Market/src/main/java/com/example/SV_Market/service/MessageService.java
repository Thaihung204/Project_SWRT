package com.example.SV_Market.service;


import com.example.SV_Market.entity.Message;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.MessageRepository;
import com.example.SV_Market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Message sendMessage(String senderId, String receiverId, String content) {

        Message message = new Message();
        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new RuntimeException("Receiver not found"));
        message.setContent(content);
        message.setCreatedAt(LocalDate.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenUsers(String senderId, String receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Người gửi không tồn tại với ID: " + senderId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Người nhận không tồn tại với ID: " + receiverId));

        return messageRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByCreatedAt(sender, receiver, receiver, sender);
    }
}
