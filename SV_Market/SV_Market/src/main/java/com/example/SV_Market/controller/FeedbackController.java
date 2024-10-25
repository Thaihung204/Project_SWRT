package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.FeedBack;
import com.example.SV_Market.request.FeedbackCreationRequest;
import com.example.SV_Market.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping()
    FeedBack createFeedback(@RequestBody FeedbackCreationRequest request){
        return feedbackService.createFeedback(request);
    }

    @GetMapping()
    List<FeedBack> getFeedback(String productId){
        return feedbackService.getFeedbacks(productId);
    }

    @DeleteMapping("/{feedbackId}")
    public String deleteFeedback(@PathVariable String feedbackId){
        feedbackService.deleteFeedback(feedbackId);
        return "Feedback has been deleted";
    }
//    @GetMapping("/{categoryId}")
//    Category getCategory(@PathVariable String categoryId){
//        return categoryService.getCategory(categoryId);
//    }
//
//    @PutMapping("/{productId}")
//    Product updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
//        return productService.updateProduct(productId, request);
//    }
}
