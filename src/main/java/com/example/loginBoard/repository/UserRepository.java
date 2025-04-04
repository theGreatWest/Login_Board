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
    @Query("update user_tb u set u.status = :status where u.id = :id")
    public void setStatus(String status, String id);

    // update user set password = ? where id = ?
    @Modifying
    @Transactional
    @Query("update user_tb u set u.password = :password where u.id = :id")
    public void setPassword(String id, String password);

    // select * from user where email = ?
    public Optional<User> findFirstByEmail(String email);

    // select * from user where phone1 = ? and phone2 = ? and  phone3 = ?
    public Optional<User>  findFirstByPhone1AndPhone2AndPhone3(String phone1, String phone2, String phone3);
}
