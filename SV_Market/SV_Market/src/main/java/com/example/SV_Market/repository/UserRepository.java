 package com.example.SV_Market.repository;

 import com.example.SV_Market.entity.Status;
 import com.example.SV_Market.entity.User;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;

 import java.util.List;
 import java.util.Optional;

 public interface UserRepository extends JpaRepository<User,String> {
  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findByEmail(String email);
  @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
  Optional<User> login(String email, String password);

     List<User> findAllByStatus(Status status);
 }
