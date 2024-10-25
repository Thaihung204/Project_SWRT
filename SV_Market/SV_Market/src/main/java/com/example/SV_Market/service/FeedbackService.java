package com.example.SV_Market.service;

import com.example.SV_Market.entity.FeedBack;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.repository.FeedbackRepository;
import com.example.SV_Market.repository.ProductImageRepository;
import com.example.SV_Market.request.FeedbackCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    public FeedBack createFeedback(FeedbackCreationRequest request){
        LocalDate currentDate = LocalDate.now();
        FeedBack feedBack = new FeedBack();
        feedBack.setSender(userService.getUserById(request.getSenderId()));
        feedBack.setReceiver(userService.getUserById(request.getReceiverId()));
        feedBack.setProduct(productService.getProductById(request.getProductId()));
        feedBack.setRating(request.getRating());
        feedBack.setDescription(request.getDescription());
        feedBack.setCreatedAt(currentDate);
        return feedbackRepository.save(feedBack);
    }

    public List<FeedBack> getFeedbacks(String productId) {
        return (List<FeedBack>) feedbackRepository.findById(productId).orElseThrow(() -> new RuntimeException("Feedback Not Found!"));
    }

    public void deleteFeedback (String feedbackId){
        feedbackRepository.deleteById(feedbackId);
    }


}
