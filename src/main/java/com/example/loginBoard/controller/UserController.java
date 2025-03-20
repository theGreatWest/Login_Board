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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor

@Slf4j

@RestController
@RequestMapping("/login_board/user")
public class UserController {

    private final UserService service;
    private final EmailService emailService;

    private Map<String, String> authenticationNumber = new HashMap<>();
    private int loginCount;

    // 회원 전체 보기
    @GetMapping("view_all")
    public List<User> viewAllUsers() {
        return service.viewAllUsers();
    }

    // ID로 조회하기
    @PostMapping("view_target")
    public User viewTarget(
            @Valid @RequestBody IdDto request
    ){
        var user = service.viewTargetUser(request);

        if(user.isPresent()) {
            log.info("{}", user);
            return user.get();
        }

        return null;
    }

    // 아이디 중복 여부 체크
    // 최소 4글자 체크
    @PostMapping("/check_id")
    public IdDto checkId(
            @Valid @RequestBody IdDto request
    ) {

        log.info("입력된 ID : {}\n", request.toString());

        if (!service.checkIdDuplication(request)) {
            authenticationNumber.put(request.getId(), null);
            return request;
        }
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
    public PasswordDto checkPassword(
            @Valid @RequestBody PasswordDto request
    ) {
//        String regex = ".*[!~*^].*";

        log.info("입력된 Password : {}\n", request.toString());

//        if(request.getPassword().matches(regex)) return request;

        return request;
    }

    // 핸드폰 번호 확인 - 본인인증 + 인증 번호 다시 보내기 클릭 시 실행
    @PostMapping("/check_phone")
    public CertificationDto checkPhone(
            @Valid @RequestBody PhoneDto request
    ) {
        log.info("입력된 phone number : {}\n", request.toString());

        CertificationDto certification = SmsService.send(request.getId().trim(), request.getPhoneFull());
        authenticationNumber.put(certification.getId().trim(), certification.getCertificationNumber());

        return certification;
    }

    // 이메일 확인 - 본인인증 + 인증 번호 다시 보내기 클릭 시 실행
    @PostMapping("/check_email")
    public CertificationDto checkEmail(
            @Valid @RequestBody EmailDto request
    ) {
        log.info("입력된 email : {}\n", request.getFull());

        CertificationDto certification = emailService.sendVerificationEmail(request.getId().trim(), request.getFull());
        authenticationNumber.put(certification.getId().trim(), certification.getCertificationNumber());

        return certification;
    }

    // 핸드폰, 이메일 등 본인 인증 -> 인증 번호를 입력 후 일치하는지 확인: 일치하면 본인 인증 완료
    @PostMapping("/authentication_confirm")
    public boolean authentication(
           @Valid @RequestBody CertificationDto request
    ) {
        String inputNumber = request.getCertificationNumber().trim();

        boolean result = authenticationNumber.get(request.getId().trim()).equals(inputNumber);

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

            log.info("📍비활성화 계정입니다.");
            return false;
        }

        loginCount++;
        log.info("로그인 시도 횟수 : {}",loginCount);

        if(loginCount == 5){
            loginCount = 0;
            service.changeStatus(user, "INACTIVE");
            log.info("📍계정이 비활성화 되었습니다. 본인 인증을 통해 계정을 활성화 할 수 있습니다.");
        }
        return false;
    }

    // 비밀번호 재설정
    @PostMapping("/update_password")
    public void updatePassword(
            @Valid @RequestBody LoginDto request
    ){
        // 핸드폰과 이메일 본인 인증 중 택 1 -> 인증 코드 받아 본인인증 완료되면 해당 메서드 실행
        // 아이디가 존재하는지 확인 -> 존재한다면 id 확인 후 비밀번호 재설정
        service.changePassword(request);
    }

    // 아이디 찾기 : 핸드폰 번호 인증
    // 등록된 핸드폰 또는 이메일으로 본인 인증 진행 -> 성공 시, 선택한 인증 수단으로 아이디 전송
    @PostMapping("/find_id_phone")
    public FindIdDto findIdPhone(
            @Valid @RequestBody PhoneDto request
    ){
        FindIdDto user = service.findIdByPhone(request);

        if(user==null){
            log.info("등록되지 않은 번호입니다.");
            return null;
        }

        SmsService.sendId(convertValue(user.getId()), user.getPhone());

        return user;
    }

    // 아이디 찾기 : 이메일 인증
    // 등록된 핸드폰 또는 이메일으로 본인 인증 진행 -> 성공 시, 선택한 인증 수단으로 아이디 전송
    @PostMapping("/find_id_email")
    public FindIdDto findIdEmail(
            @Valid @RequestBody EmailDto request
    ){
        FindIdDto user = service.findIdByEmail(request);

        if(user==null){
            log.info("등록되지 않은 이메일 주소입니다.");
            return null;
        }

        emailService.sendId(convertValue(user.getId()), user.getEmail());

        return user;
    }

    private String convertValue(String value){
        StringBuilder sb = new StringBuilder();

        int size = value.length();
        int start = size/3, end = size/2 + start - 1;

        for(int i=0;i<size;i++){
            if(i>=start && i<=end) sb.append("*");
            else sb.append(value.charAt(i));
        }

        return sb.toString();
    }

}
