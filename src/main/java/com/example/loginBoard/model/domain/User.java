package com.example.loginBoard.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueNumber;

    @NotBlank
    private String id;

    @NotBlank
    @Size(min = 6, max = 12)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String phone1;

    @NotBlank
    private String phone2;

    @NotBlank
    private String phone3;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime lastLoginAt;

    @NotBlank
    private String status;

    @Transient
    public String getPhoneFull() {
        return "+82" + this.phone1.substring(1, 3) + this.phone2 + this.phone3;
    }
}
