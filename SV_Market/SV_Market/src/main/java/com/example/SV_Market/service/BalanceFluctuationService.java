package com.example.SV_Market.service;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.Category;
import com.example.SV_Market.repository.BalanceFluctuationRepository;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.CategoryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceFluctuationService {

    @Autowired
    private BalanceFluctuationRepository balanceFluctuationRepository;

    @Autowired
    private UserService userService;

    public BalanceFluctuation createBalanceFluctuation(String userId, String type, String amountS, String content){
        BalanceFluctuation bf = new BalanceFluctuation();
        Double amount = Double.parseDouble(amountS);
        if(userService.getUserById(userId)==null)
            throw new RuntimeException("User not found");
        bf.setUser(userService.getUserById(userId));
        double newBalance =0;
        bf.setTransactionType(type);
        bf.setAmount(amount);
        if(type.equals("+"))
            newBalance = userService.getUserById(userId).getBalance() + amount;
        if(type.equals("-"))
            newBalance = userService.getUserById(userId).getBalance() - amount;
        if (newBalance <= 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        userService.getUserById(userId).setBalance(newBalance); // Update the user's balance
        bf.setBalance(newBalance);
        bf.setContent(content);
        bf.setState("successful");
        bf.setDate(LocalDate.now());
        return balanceFluctuationRepository.save(bf);
    }

    public List<BalanceFluctuation> getBalanceFluctustionByUser(String userId){
        return balanceFluctuationRepository.findByUserId(userId);
    }


}
