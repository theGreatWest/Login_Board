package com.example.loginBoard.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {
    private String email1;
    private String email2;

    public String getFull(){
        return email1 + "@" + email2;
    }
}
