package com.example.loginBoard.external.email;

import com.example.loginBoard.model.dto.user.CertificationDto;
import com.example.loginBoard.model.dto.user.IdDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public CertificationDto sendVerificationEmail(String id, String email) {
        String code = generateVerificationCode();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("이메일 인증 코드");
        msg.setText("인증 코드: " + code);

        mailSender.send(msg);

        log.info("인증 코드 이메일 전송 : {}", "인증 코드 전송 완료!");

        return CertificationDto.builder()
                .id(id)
                .email(email)
                .certificationNumber(code)
                .build();
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(1000000)); // 6자리 랜덤 숫자 생성
    }

    public String sendId(String id, String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("아이디 찾기");
        msg.setText("아이디: " + id);

        mailSender.send(msg);

        log.info("아이디/비밀번호 찾기 이메일 전송 : {}", "발송 성공");

        return id;
    }
}
