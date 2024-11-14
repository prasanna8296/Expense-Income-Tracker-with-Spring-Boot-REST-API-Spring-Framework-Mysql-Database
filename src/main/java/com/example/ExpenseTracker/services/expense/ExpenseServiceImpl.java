package com.example.ExpenseTracker.services.expense;

import com.example.ExpenseTracker.dto.ExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.repo.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDto expenseDto){
        return savaOrUpdateExpense(new Expense(),expenseDto);
    }

    public Expense updateExpense(Long id,ExpenseDto expenseDto)
    {

        Optional<Expense>optionalExpense=expenseRepository.findById(id);
        if(optionalExpense.isPresent())
        {
            return savaOrUpdateExpense(optionalExpense.get(),expenseDto);
        }
        else{
            throw  new EntityNotFoundException("Expense not present with id "+id);
        }

    }

    private Expense savaOrUpdateExpense(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());

        return expenseRepository.save(expense);
    }


    public List<Expense>getAllExpense()
    {
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id)
    {
        Optional<Expense> optionalExpense=expenseRepository.findById(id);
        if(optionalExpense.isPresent())
        {
            return optionalExpense.get();

        }
        else {
            throw new EntityNotFoundException("Expense not present with id " + id);
        }
    }

    public void deleteExpense(Long id)
    {
        Optional<Expense>optionalExpense=expenseRepository.findById(id);
        if(optionalExpense.isPresent())
        {
            expenseRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Expense is not present with id "+id);
        }
    }


}

