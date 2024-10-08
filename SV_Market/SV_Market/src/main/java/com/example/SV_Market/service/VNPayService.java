package com.example.SV_Market.service;


import com.example.SV_Market.config.VNPayConfig;
import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.PaymentRepository;
import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.request.PaymentRequest;
import com.example.SV_Market.request.TransactionRequest;
import com.example.SV_Market.utils.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class VNPayService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    public String pay(PaymentRequest request) throws UnsupportedEncodingException {
        long amount = request.getAmount()*100;
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VNPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        String orderType = "other";
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_ReturnUrl", request.getVnp_ReturnUrl());
        vnp_Params.put("vnp_IpAddr", VNPayConfig.vnp_IpAddr());

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    public User transaction(TransactionRequest request){
        User user = getUser(request.getUserId());
        double balance = user.getBalance();
        TransactionUtil transactionUtil = new TransactionUtil();
//        String state =
        if (request.getVnp_ResponseCode().equals("00")){
            balance += request.getVnp_Amount()/100;
        }
        request.setDate();
        user.setBalance(balance);
        BalanceFluctuation balanceFluctuation = BalanceFluctuation.builder()
                .user(user)
                .transactionType("+")
                .amount(request.getVnp_Amount())
                .balance(balance)
                .content(request.getVnp_OrderInfo())
                .date(request.getDate())
                .state(transactionUtil.getType(request.getVnp_ResponseCode()))
                .build();
        paymentRepository.save(balanceFluctuation);
        return userRepository.save(user);
    }
    public User getUser(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
