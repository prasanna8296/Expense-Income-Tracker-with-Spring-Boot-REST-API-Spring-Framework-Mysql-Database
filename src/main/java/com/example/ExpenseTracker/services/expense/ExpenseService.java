package com.example.ExpenseTracker.services.expense;

import com.example.ExpenseTracker.dto.ExpenseDto;
import com.example.ExpenseTracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);

    List<Expense>getAllExpense();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id,ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
