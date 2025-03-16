package com.example.loginBoard.repository;

import com.example.loginBoard.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // select * from user where id = ?
    public Optional<User> findFirstById(String id);

    // select * from user where nickname = ?
    public Optional<User> findFirstByNickname(String nickname);
}
