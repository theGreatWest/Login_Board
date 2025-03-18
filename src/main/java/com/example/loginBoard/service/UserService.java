package com.example.loginBoard.service;

import com.example.loginBoard.model.domain.User;
import com.example.loginBoard.model.dto.user.*;
import com.example.loginBoard.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository repository;

    public List<User> viewAllUsers(){
        return repository.findAll();
    }

    public Optional<User> viewTargetUser(IdDto request){
        return repository.findFirstById(request.getId());
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

    public boolean login(LoginDto response) {
        String id = response.getId();
        String password = response.getPassword();

        return repository.findFirstByIdAndPassword(id, password).isPresent();
    }

    public boolean checkStatus(IdDto id, String status) {
        return repository.findFirstByIdAndStatus(id.getId(), status).isPresent();
    }

    public void changeStatus(IdDto id, String status) {
        repository.setStatus(status, id.getId());
    }

    public void changePassword(LoginDto loginDto){
        repository.setPassword(loginDto.getId(), loginDto.getPassword());
    }
}
