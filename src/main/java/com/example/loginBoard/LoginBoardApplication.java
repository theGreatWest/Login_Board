package com.example.loginBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케쥴링 활성화

@SpringBootApplication
public class LoginBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginBoardApplication.class, args);
	}

}
