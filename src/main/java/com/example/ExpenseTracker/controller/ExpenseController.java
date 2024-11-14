package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.dto.ExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.services.expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?>postExpense(@RequestBody ExpenseDto dto)
    {
        Expense createdExpense=expenseService.postExpense(dto);
        if(createdExpense!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?>getAllExpense()
    {
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getExpenseById(@PathVariable Long id)
    {
        try{
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateExpense(@PathVariable Long id,@RequestBody ExpenseDto dto)
    {
        try{
            return ResponseEntity.ok(expenseService.updateExpense(id,dto));
        }catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteExpense(@PathVariable Long id)
    {
        try{
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(null);
        }catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }


}
