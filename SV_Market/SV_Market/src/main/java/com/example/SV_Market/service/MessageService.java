package com.example.SV_Market.service;


import com.example.SV_Market.entity.Message;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.MessageRepository;
import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public Message sendMessage(MessageRequest messageDTO) {
        User sender = userRepository.findById(messageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageDTO.getContent());
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getMessages(String senderId, String receiverId) {
        // Lấy thông tin User từ ID
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);

        return messageRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(sender, receiver, receiver, sender);
    }
    }


