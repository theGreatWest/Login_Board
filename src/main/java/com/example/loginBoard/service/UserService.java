package com.example.loginBoard.service;

import com.example.loginBoard.model.domain.User;
import com.example.loginBoard.model.dto.user.IdDto;
import com.example.loginBoard.model.dto.user.NicknameDto;
import com.example.loginBoard.model.dto.user.UserDto;
import com.example.loginBoard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository repository;

    public List<User> viewAllUsers(){
        return repository.findAll();
    }

    public boolean checkIdDuplication(IdDto request) {
        return repository.findFirstById(request.getId().trim()).isPresent();
    }

    public boolean checkNicknameDuplication(NicknameDto request) {
        return repository.findFirstByNickname(request.getNickname().trim()).isPresent();
    }

    public User signup(UserDto request){
        User user = User.builder()
                .id(request.getId().trim())
                .password(request.getPassword().trim())
                .nickname(request.getNickname().trim())
                .phone1(request.getPhone1())
                .phone2(request.getPhone2())
                .phone3(request.getPhone3())
                .email(request.getEmail())
                .lastLoginAt(LocalDateTime.now())
                .status("ACTIVE")
                .build();

        return repository.save(user);
    }
}
