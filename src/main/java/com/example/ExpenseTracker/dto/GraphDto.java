package com.example.ExpenseTracker.dto;

import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GraphDto {

    private List<Expense>expensesList;

    private List<Income>incomeList;
}
