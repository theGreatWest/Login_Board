package com.example.loginBoard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api <T>{
    private T data;

    private Integer resultCode;
    private String resultMsg;
}
