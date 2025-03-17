package com.example.loginBoard.repository;

import com.example.loginBoard.model.domain.User;
import com.example.loginBoard.model.dto.user.LoginDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // select * from user where id = ?
    public Optional<User> findFirstById(String id);

    // select * from user where nickname = ?
    public Optional<User> findFirstByNickname(String nickname);

    // select * from user where id = ? and password = ?;
    public Optional<User> findFirstByIdAndPassword(String id, String password);

    // select * from user where id = ? and status = ?
    public Optional<User> findFirstByIdAndStatus(String id, String status);

    // update user set status = ? where id = ?
    @Modifying
    @Transactional
    @Query("update user_tb u set u.status = :status WHERE u.id = :id")
    public void setStatus(String status, String id);
}
