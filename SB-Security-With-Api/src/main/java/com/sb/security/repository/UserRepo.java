package com.sb.security.repository;

import com.sb.security.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String userName);
}
