package com.example.SV_Market.service;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.ChatRoom;
import com.example.SV_Market.repository.BalanceFluctuationRepository;
import com.example.SV_Market.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatroomRepository;

    public Optional<String> getChatRoomid(String senderId, String receiverId, Boolean newRoom){
        return chatroomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatRoomId)
                .or(()->{
                    if(newRoom){
                        var chatId = createChatRoom(senderId, receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });

    }


    public String createChatRoom(String senderId, String receiverId) {
    var chatId = String.format("%s_%s", senderId, receiverId);
    ChatRoom senderReceiver= ChatRoom.builder()
            .chatRoomId(chatId)
            .senderId(senderId)
            .receiverId(receiverId)
            .build();
        ChatRoom receiverSender = ChatRoom.builder()
                .chatRoomId(chatId)
                .senderId(receiverId)
                .receiverId(senderId)
                .build();
        chatroomRepository.save(receiverSender);
        chatroomRepository.save(senderReceiver);
        return chatId;
    }

}
