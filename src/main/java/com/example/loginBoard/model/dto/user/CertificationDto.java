package com.example.loginBoard.model.dto.user;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationDto {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String certificationNumber;

    @Size(min = 11, max = 11)
    private String phone;

    @Email
    private String email;
}
