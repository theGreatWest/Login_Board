package com.example.loginBoard.service;

import com.example.loginBoard.model.domain.User;
import com.example.loginBoard.model.dto.user.*;
import com.example.loginBoard.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class UserService {

    private final UserRepository repository;

    public List<User> viewAllUsers() {
        return repository.findAll();
    }

    public Optional<User> viewTargetUser(IdDto request) {
        return repository.findFirstById(request.getId());
    }

    public boolean checkIdDuplication(IdDto request) {
        return repository.findFirstById(request.getId().trim()).isPresent();
    }

    public boolean checkNicknameDuplication(NicknameDto request) {
        return repository.findFirstByNickname(request.getNickname().trim()).isPresent();
    }

    public User signup(UserDto request) {
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

    public void changePassword(LoginDto loginDto) {
        repository.setPassword(loginDto.getId(), loginDto.getPassword());
    }

    public FindIdDto findIdByEmail(EmailDto emailDto) {
        var user = repository.findFirstByEmail(emailDto.getFull());

        return user.map(value ->
                FindIdDto.builder()
                        .id(value.getId())
                        .email(value.getEmail())
                        .build()
        ).orElse(null);
    }

    public FindIdDto findIdByPhone(PhoneDto phoneDto) {
        var user = repository.findFirstByPhone1AndPhone2AndPhone3(phoneDto.getPhone1(), phoneDto.getPhone2(), phoneDto.getPhone3());

        return user.map(value ->
                FindIdDto.builder()
                        .id(value.getId())
                        .phone(value.getPhoneFull())
                        .build()
        ).orElse(null);
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정 00:00 에 실행되는 메서드
//    @Scheduled(cron = "0 57 12 * * ?") // 매일 12 : 37분 에 실행되는 메서드
//    @Scheduled(fixedRate = 50000) // 10초마다 실행 : 개발할 때만 이용
//    @Scheduled(cron = "0 30 14 * * ?") // 매일 오후 2시 30분 실행
//    @Scheduled(cron = "0 0 12 ? * MON") // 매주 월요일 12시 실행
//    @Scheduled(fixedDelay = 5000) // 이전 작업이 끝난 후 5초 뒤 실행
//    @Scheduled(fixedRate = 5000, initialDelay = 10000) // 10초 후 첫 실행, 이후 5초마다 실행
    public void runEveryMidnight() {

        LocalDateTime oneYearAge = LocalDateTime.now().minusYears(1);

        repository.findAll().stream()
                .filter(user -> user.getLastLoginAt().isBefore(oneYearAge))
                .forEach(user -> {
                    if (user.getStatus().equals("ACTIVE")) repository.setStatus("INACTIVE", user.getId());
                });
    }
}
