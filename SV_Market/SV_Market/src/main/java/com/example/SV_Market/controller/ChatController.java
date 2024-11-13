package com.example.SV_Market.controller;

import com.example.SV_Market.entity.ChatMessage;
import com.example.SV_Market.entity.ChatNotification;

import com.example.SV_Market.service.ChatMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message/{senderid}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> findChatmessage(
            @PathVariable("senderId") String senderId,
            @PathVariable("receiverId") String receiverId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessage(senderId,receiverId));
    }
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage saveMsg = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(), "/queue/message",
                ChatNotification.builder()
                        .id(saveMsg.getId())
                        .senderId(saveMsg.getSenderId())
                        .receiverId(saveMsg.getReceiverId())
                        .content(saveMsg.getContent())
                        .build()
        );
    }

}
