package com.back_hack.security.repository;

import com.back_hack.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
