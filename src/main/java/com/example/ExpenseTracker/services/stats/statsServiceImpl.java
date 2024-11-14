package com.example.ExpenseTracker.services.stats;

import com.example.ExpenseTracker.dto.GraphDto;
import com.example.ExpenseTracker.dto.StatsDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.repo.ExpenseRepository;
import com.example.ExpenseTracker.repo.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class statsServiceImpl implements statsService{

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public GraphDto getChartData(){
        LocalDate endDate=LocalDate.now();
        LocalDate startDate=endDate.minusDays(27);

        GraphDto graphDto=new GraphDto();
        graphDto.setExpensesList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate,endDate));

        return graphDto;
    }
    public StatsDto getStats()
    {
        Double totalIncome=incomeRepository.sumAllAmount();
        Double totalExpense=expenseRepository.sumAllAmount();

        Optional<Income>optionalIncome=incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense>optionalExpense=expenseRepository.findFirstByOrderByDateDesc();



        StatsDto statsDto=new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setExpense(totalIncome);

        optionalIncome.ifPresent(statsDto::setLatestIncome);
        optionalExpense.ifPresent(statsDto::setLatestExpense);

        statsDto.setBalance(totalIncome-totalExpense);

        List<Income>incomeList=incomeRepository.findAll();
        List<Expense>expenseList=expenseRepository.findAll();

        OptionalDouble minIncome=incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome=incomeList.stream().mapToDouble(Income::getAmount).max();
        OptionalDouble minExpense=expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense=expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxExpense(maxExpense.isPresent()?maxExpense.getAsDouble():null);
        statsDto.setMinExpense(minExpense.isPresent()?minExpense.getAsDouble():null);
        statsDto.setMaxIncome(maxIncome.isPresent()?maxIncome.getAsDouble():null);
        statsDto.setMinIncome(minIncome.isPresent()?minIncome.getAsDouble():null);



        return statsDto;
    }
}
