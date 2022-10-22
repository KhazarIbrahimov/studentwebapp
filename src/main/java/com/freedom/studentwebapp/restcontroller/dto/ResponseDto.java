package com.freedom.studentwebapp.restcontroller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDto<T> {

    private String message;
    private LocalDateTime date = LocalDateTime.now();
    private int code;
    private T data;

}
