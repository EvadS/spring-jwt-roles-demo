package com.login.demo.repository;

import com.login.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

}