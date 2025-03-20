package com.example.loginBoard.model.dto.user;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String email1;

    @NotBlank
    private String email2;

    public String getFull(){
        return email1 + "@" + email2;
    }
}
