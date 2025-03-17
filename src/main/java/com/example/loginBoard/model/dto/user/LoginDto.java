package com.example.loginBoard.model.dto.user;

import com.example.loginBoard.annotation.Password;
import jakarta.persistence.Id;
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
public class LoginDto {
    @Id
    @NotBlank
    private String id;

    @Password
    @Size(min = 6, max = 12)
    private String password;
}
