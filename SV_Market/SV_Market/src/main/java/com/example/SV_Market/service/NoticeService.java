package com.example.SV_Market.service;

import com.example.SV_Market.entity.Notice;
import com.example.SV_Market.entity.Order;
import com.example.SV_Market.entity.OrderDetail;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.NoticeRepository;
import com.example.SV_Market.repository.OrderRepository;
import com.example.SV_Market.request.OrderCreationRequest;
import com.example.SV_Market.response.OrderDetailResponse;
import com.example.SV_Market.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService ;

    public Notice createNotice(String userId, String title, String content) {
        // Find user by userId
        OrderRepository userRepository;
        User user = userService.getUserById(userId);
        // Create new Notice
        Notice notice = new Notice();
        notice.setUser(user);  // Set the user
        notice.setTitle(title);  // Set the notice title
        notice.setContent(content);  // Set the notice content
        notice.setCreateAt(LocalDate.now());  // Set the current date as createAt
        // Save Notice to the database
        return noticeRepository.save(notice);
    }

    public List<Notice> getNoticeByUser(String userId){
        return noticeRepository.getNoticeByUser(userId);
    }

}
