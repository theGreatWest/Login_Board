package com.example.loginBoard.annotation;

import com.example.loginBoard.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PasswordValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "비밀번호 조건에 맞지 않습니다.";
    String regexp() default ".*[!~*^].*";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
