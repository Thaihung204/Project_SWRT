package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Message;
import com.example.SV_Market.request.MessageRequest;
import com.example.SV_Market.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messagerequest) {
        Message sentMessage = messageService.sendMessage(messagerequest);
        return ResponseEntity.ok(sentMessage);
    }


    @GetMapping
    public List<Message> getMessagesBetweenUsers(@RequestParam String senderId, @RequestParam String receiverId) {
        return messageService.getMessages(senderId, receiverId);
    }
}
