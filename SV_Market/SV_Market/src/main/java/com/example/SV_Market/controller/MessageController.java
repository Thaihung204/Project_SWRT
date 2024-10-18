package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Message;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.MessageRepository;
import com.example.SV_Market.request.MessageRequest;
import com.example.SV_Market.service.MessageService;
import com.example.SV_Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageRequest sendMessage(MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest.getSenderId(), messageRequest.getReceiverId(), messageRequest.getContent());
        return messageRequest;
    }

    @PostMapping("/send")
    public MessageRequest sendMessageREST(@RequestBody MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest.getSenderId(), messageRequest.getReceiverId(), messageRequest.getContent());
        return messageRequest;
    }

    @GetMapping("/getMessage")
    public List<Message> getMessageHistory(@RequestParam String senderId, @RequestParam String receiverId) {
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);
        return messageService.getMessagesBetweenUsers(senderId, receiverId);
    }
}
