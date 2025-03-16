package com.example.loginBoard.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDto {
    @NotBlank
    @Size(min = 3, max = 3)
    private String phone1;

    @NotBlank
    @Size(min = 4, max = 4)
    private String phone2;

    @NotBlank
    @Size(min = 4, max = 4)
    private String phone3;

    @Transient
    public String getFull() {
        return "+82" + this.phone1.substring(1, 3) + this.phone2 + this.phone3;
    }
}
