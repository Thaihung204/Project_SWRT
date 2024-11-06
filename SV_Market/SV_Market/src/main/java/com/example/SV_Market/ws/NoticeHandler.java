package com.example.SV_Market.ws;

import com.example.SV_Market.entity.Notice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class NoticeHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        log.info("Received message: {}", message.getPayload());
//
//        Notice notice = new Notice();
//        notice.setNoticeId("123");
//        notice.setTitle("New Notice");
//        notice.setContent("This is a sample notice");
//        notice.setCreateAt(LocalDate.now());
//        String noticeJson = objectMapper.writeValueAsString(notice);
//        session.sendMessage(new TextMessage(noticeJson));
//    }

    public void handerNotice( Notice notice) throws IOException {
        WebSocketSession session = null;
        String noticeJson = objectMapper.writeValueAsString(notice);
        session.sendMessage(new TextMessage(noticeJson));


    }
}
