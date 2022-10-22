package com.freedom.studentwebapp.restcontroller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TeacherDto {

    private int id;
    private String name;
    private String surname;
    private BigDecimal salary;
    private Integer universityId;
}
