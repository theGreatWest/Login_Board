package com.example.loginBoard.external.sms;

import com.example.loginBoard.model.dto.user.CertificationDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class SmsService {
    static final String SID = "///";
    static final String TOKEN = "///";
    static final String FROM_PHONE = "///";

    public static CertificationDto send(String toPhone){
        Twilio.init(SID, TOKEN);

        String certificationNum = generateVerificationCode();

        Message msg = Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(FROM_PHONE),
                String.format("[ 인증 번호 ] %s", certificationNum)
        ).create();

        log.info("인증버호 발송 성공 : {}", msg.getSid());

        return CertificationDto.builder()
                .certificationNumber(certificationNum)
                .build();
    }

    private static String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(1000000)); // 6자리 랜덤 숫자 생성
    }
}
