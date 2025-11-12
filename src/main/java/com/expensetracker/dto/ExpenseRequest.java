package com.expensetracker.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for creating an Expense.
 * Client should send:
 * {
 *   "title":"Coffee",
 *   "amount":2.5,
 *   "date":"2025-11-12",
 *   "categoryId": 1
 * }
 */
@Data
public class ExpenseRequest 
{
    @NotNull
    @Size(min = 1)
    private String title;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private Long categoryId;
}
