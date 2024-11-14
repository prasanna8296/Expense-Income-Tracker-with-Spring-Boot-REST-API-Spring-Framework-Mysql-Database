package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

    //i need to get the info from the two dates
    List<Income>findByDateBetween(LocalDate startDate,LocalDate endDate);

    @Query("SELECT SUM(i.amount) FROM Income i")
    Double sumAllAmount();

    Optional<Income> findFirstByOrderByDateDesc();
}
