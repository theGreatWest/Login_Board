package com.example.loginBoard.repository;

import com.example.loginBoard.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
