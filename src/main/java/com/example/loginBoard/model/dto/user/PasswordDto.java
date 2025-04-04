package com.example.loginBoard.model.dto.user;

import com.example.loginBoard.annotation.Password;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordDto {
    @NotBlank
    @Size(min = 6, max = 12)
    @Password
//    @Pattern(regexp = ".*[!~*^].*", message = "비밀번호가 조건을 만족하지 않습니다.")
    private String password;
}
