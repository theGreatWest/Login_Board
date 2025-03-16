package com.example.loginBoard.model.dto.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
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
}
