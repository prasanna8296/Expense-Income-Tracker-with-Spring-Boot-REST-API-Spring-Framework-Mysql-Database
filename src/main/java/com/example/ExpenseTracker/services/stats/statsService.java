package com.example.ExpenseTracker.services.stats;

import com.example.ExpenseTracker.dto.GraphDto;
import com.example.ExpenseTracker.dto.StatsDto;

public interface statsService {
    GraphDto getChartData();
    StatsDto getStats();
}
