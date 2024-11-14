package com.example.ExpenseTracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {
    private Long id;

    private String title;

    private Integer amount;

    private LocalDate date;

    private String category;

    private String description;
}
