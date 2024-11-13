package com.example.SV_Market.service;

import com.example.SV_Market.entity.ChatMessage;
import com.example.SV_Market.entity.ChatRoom;
import com.example.SV_Market.repository.ChatMessageRepository;
import com.example.SV_Market.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    private ChatRoomService chatRoomService;


    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatRoomid(chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                true
        ).orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;



    }

    public List<ChatMessage> findChatMessage(String senderId, String receiverId){
        var chatId = chatRoomService.getChatRoomid(senderId,receiverId,false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }

}
