package com.example.SV_Market.service.impl;


import com.example.SV_Market.dto.DataMailDTO;
import com.example.SV_Market.dto.DataOtp;
import com.example.SV_Market.dto.sdi.ClientSdi;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.service.ClientService;
import com.example.SV_Market.service.MailService;
import com.example.SV_Market.utils.Const;
import com.example.SV_Market.utils.DataUtils;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    private ConcurrentHashMap<String, DataOtp> otpStore = new ConcurrentHashMap<>();
    private static final int EXPIRY_MINUTES = 15;

    @Override
    public Boolean create(ClientSdi sdi) {

        try {
            if (userRepository.findByEmail(sdi.getEmail()).isEmpty()) {
                DataMailDTO dataMail = new DataMailDTO();

                dataMail.setTo(sdi.getEmail());
                dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

                String otp = DataUtils.generateTempPwd(6);
                LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
                DataOtp dataOtp = new DataOtp(otp, expiryTime);
                otpStore.put(sdi.getEmail(), dataOtp);
                Map<String, Object> props = new HashMap<>();
                props.put("name", sdi.getFirstName());
                props.put("email", sdi.getEmail());
                props.put("otp", otp);
                dataMail.setProps(props);

                mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
                return true;
            } else {
                throw new RuntimeException("Email đã đăng ký.");
            }
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Boolean forget(String email) {
        try {
            if (userRepository.findByEmail(email).isEmpty()) {
                throw new RuntimeException("Email không tìm thấy.");
            } else {

                User user = userRepository.findByEmail(email).get();
                DataMailDTO dataMail = new DataMailDTO();

                dataMail.setTo(email);
                dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

                String otp = DataUtils.generateTempPwd(6);
                LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
                DataOtp dataOtp = new DataOtp(otp, expiryTime);
                otpStore.put(email, dataOtp);
                Map<String, Object> props = new HashMap<>();
                props.put("name", user.getUserName());
                props.put("email", user.getEmail());
                props.put("otp", otp);
                dataMail.setProps(props);

                mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
                log.info(otp);
                log.info("OTP: " + layOtp(email));
                return true;
            }
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public String layOtp(String email) {
        DataOtp otpEntry = otpStore.get(email);
        if (otpEntry != null && LocalDateTime.now().isBefore(otpEntry.getExpiryTime())) {
//            otpStore.remove(email);
            log.info(otpEntry.getOtp());
            return otpEntry.getOtp();
        }
        return null;
    }

    public boolean isValidEmail(String email) {
        // Biểu thức chính quy cho định dạng email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        // Tạo đối tượng Pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Tạo đối tượng Matcher
        Matcher matcher = pattern.matcher(email);

        // Kiểm tra chuỗi với biểu thức chính quy
        return matcher.matches();
    }
}
