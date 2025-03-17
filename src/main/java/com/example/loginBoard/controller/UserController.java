package com.example.loginBoard.controller;

import com.example.loginBoard.model.domain.User;
import com.example.loginBoard.model.dto.user.*;
import com.example.loginBoard.service.UserService;
import com.example.loginBoard.external.email.EmailService;
import com.example.loginBoard.external.sms.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Slf4j

@RestController
@RequestMapping("/login_board/user")
public class UserController {

    private final UserService service;
    private final EmailService emailService;

    private CertificationDto authenticationNumber;
    private int loginCount;

    // 회원 전체 보기
    @GetMapping("view_all")
    public List<User> viewAllUsers() {
        return service.viewAllUsers();
    }

    // 아이디 중복 여부 체크
    // 최소 4글자 체크
    @PostMapping("/check_id")
    public IdDto checkId(
            @Valid @RequestBody IdDto request
    ) {

        log.info("입력된 ID : {}\n", request.toString());

        if (!service.checkIdDuplication(request)) return request;
        else return null; // 다시 입력 받아야 함
    }

    // 닉네임 중복 여부 체크
    @PostMapping("/check_nickname")
    public boolean checkNickName(
            @Valid @RequestBody NicknameDto request
    ) {
        log.info("입력된 Nickname : {}\n", request.getNickname());

        request.setNickname(request.getNickname().trim());

        return !service.checkNicknameDuplication(request);// 다시 입력 받아야 함
    }

    // 비밀번호에 !, *, ~, ^ 포함되는지 확인 -- 이 부분은 그냥 js 로 할 수 있을 것 같다.
    @PostMapping("/check_password")
    public PasswordDto check_password(
            @Valid @RequestBody PasswordDto request
    ) {
//        String regex = ".*[!~*^].*";

        log.info("입력된 Password : {}\n", request.toString());

//        if(request.getPassword().matches(regex)) return request;

        return request;
    }

    // 핸드폰 번호 확인 - 본인인증 + 인증 번호 다시 보내기 클릭 시 실행
    @PostMapping("/check_phone")
    public CertificationDto check_phone(
            @Valid @RequestBody PhoneDto request
    ) {
        log.info("입력된 phone number : {}\n", request.toString());

        authenticationNumber = SmsService.send(request.getFull());
        // 인증번호와 사용자가 입력한 값을 대조해 비교해야 한다.

        return authenticationNumber;
    }

    // 이메일 확인 - 본인인증 + 인증 번호 다시 보내기 클릭 시 실행
    @PostMapping("/check_email")
    public CertificationDto check_email(
            @Valid @RequestBody EmailDto request
    ) {
        log.info("입력된 email : {}\n", request.getFull());

        authenticationNumber = emailService.sendVerificationEmail(request.getFull());

        return authenticationNumber;
    }

    // 핸드폰, 이메일 등 본인 인증 -> 인증 번호를 입력 후 일치하는지 확인: 일치하면 본인 인증 완료
    @PostMapping("/authentication_confirm")
    public boolean authentication(
            @RequestParam String inputNumber
    ) {
        inputNumber = inputNumber.trim();

        boolean result = authenticationNumber.getCertificationNumber().equals(inputNumber);

        log.info("핸드폰 번호 인증 결과 : {}", result);

        return result;
    }

    // 회원가입
    @PostMapping("/signup")
    public User signup(
            @Valid @RequestBody UserDto request
    ) {
        log.info("입력된 회원 정보 : {}\n", request);

        return service.signup(request);
    }

    // 로그인
    // 일치 :
    // 불일치 : 5회 이상 잘못 입력한 경우 계정 잠금(status -> INACTIVE) + 본인 인증 완료해야 비밀번호 재설정 가능
    @PostMapping("/login")
    public boolean login(
            @Valid @RequestBody LoginDto request
    ) {
        IdDto user = IdDto.builder()
                .id(request.getId())
                .build();

        if (service.login(request)) {
            if(service.checkStatus(user, "ACTIVE")) {
                loginCount  = 0;
                return true;
            }

            log.info("📍비밀번호 재설정");
            return false;
        }

        loginCount++;
        log.info("로그인 시도 횟수 : {}",loginCount);

        if(loginCount == 5){
            loginCount = 0;
            service.changeStatus(user, "INACTIVE");
        }
        return false;
    }
}
