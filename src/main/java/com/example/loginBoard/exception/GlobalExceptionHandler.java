package com.example.loginBoard.exception;

import com.example.loginBoard.model.dto.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Api> globalException(
            Exception e
    ){
        log.error("Exception",e);

        Api response = Api.builder()
                .resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .resultMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
