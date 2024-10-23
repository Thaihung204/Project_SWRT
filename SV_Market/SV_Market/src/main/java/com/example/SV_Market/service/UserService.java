package com.example.SV_Market.service;

import com.example.SV_Market.dto.UserDto;
import com.example.SV_Market.dto.UserUpdateRequest;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.SubscriptionPackage;
import com.example.SV_Market.entity.Upgrade;
import com.example.SV_Market.entity.User;

import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.repository.SubscriptionPackageRepository;
import com.example.SV_Market.repository.UpgradeRepository;
import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.request.UpgradeRequest;
import com.example.SV_Market.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private UpgradeRepository upgradeRepository;
    @Autowired
    private SubscriptionPackageRepository packageRepository;
    private final ProductService productService;
    @Autowired
    public UserService(@Lazy ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    private ProductRepository productRepository;
    public User createUser(UserDto request) {
        User user = new User();
        // Combine firstName and lastName for userName
        String fullName = request.getFirstName() + " " + request.getLastName();
        user.setUserName(fullName);
        // Set password, email, and default role
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole("USER");  // Default role
        // Set optional fields
        user.setState("active");
        user.setAddress(request.getAddress());
        user.setBalance(0.0);  // Default balance
        user.setCreatedAt(new Date());  // Set created_at date
        // Save user to repository
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


public User getUserById(String userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found!"));
}


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    // New method to change the password of a user
    public String updatePassword(String email,String currentPassword, String newPassword) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("The given email must not be null");
        }

        User user;

        try {
            user = getUser(email);
            if (user.getPassword().equals(currentPassword)) {
                System.out.println("1");
                user.setPassword(newPassword);  // Update the password
                userRepository.save(user);  // Save the updated user
                return "Change password successful";
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return "Wrong old password";
//        user.setPassword(newPassword);  // Update the password
        // Save the updated user
    }


    public User getUser(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(UserUpdateRequest request) throws IOException {
        User user = getUser(request.getEmail());
        user.setUserName(request.getUsername());
        user.setAddress(request.getAddress());
        user.setPhoneNum(request.getPhoneNum());
        user.setAddress(request.getAddress());
        if(!request.getAvatar().isEmpty()) {
            user.setProfilePicture(cloudinaryService.upload(request.getAvatar()));
        }
        userRepository.save(user);
        return user;
    }

    public User getUserByEmailPass(String email, String password){
        return userRepository.login(email,password).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public SubscriptionPackage getPackage(Long id){
        return packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
    }
    @Transactional
    public User upgradeUserRole(UpgradeRequest request) {

        User user = getUserById(request.getUserId());
        SubscriptionPackage subscriptionPackage = getPackage(request.getPackageId());

        if (user.getBalance() >= subscriptionPackage.getPrice()) {
            user.setBalance(user.getBalance() - subscriptionPackage.getPrice());
            user.setRole(subscriptionPackage.getRoleName());
            Upgrade upgrade = new Upgrade();
            upgrade.setUser(user);
            upgrade.setSubscriptionPackage(subscriptionPackage);
            upgrade.setStartDate(LocalDate.now());
            upgrade.setEndDate(LocalDate.now().plusMonths(1));

            upgradeRepository.save(upgrade);


            return userRepository.save(user);
        } else {
            return userRepository.save(user);
        }

    }

    public User banUser(String userId){
        User user = getUserById(userId);
        user.setState("inactive");
        Period a = Period.between(LocalDate.now(), LocalDate.now().plusMonths(1));
        List<Product> products = productRepository.findAllByUserId(userId);
        for (Product product : products) {
            log.info(product.getProductName());
            productService.updateProductStatus(product.getProductId(),"hidden");
        }
        return userRepository.save(user);
    }

    public User unbanUser(String userId){
        User user = getUserById(userId);
        user.setState("active");
        Period a = Period.between(LocalDate.now(), LocalDate.now().plusMonths(1));
        List<Product> products = productRepository.findAllByUserId(userId);
        for (Product product : products) {
            log.info(product.getProductName());
            productService.updateProductStatus(product.getProductId(),"pending");
        }
        return userRepository.save(user);
    }

    public UserResponse formatUser(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setAddress(user.getAddress());
        userResponse.setProfilePicture(user.getProfilePicture());
        return userResponse;

    }
}
