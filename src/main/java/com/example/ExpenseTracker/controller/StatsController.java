package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.dto.GraphDto;
import com.example.ExpenseTracker.services.stats.statsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {

    private final statsService statsService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDto>getChartDetails()
    {
        return ResponseEntity.ok(statsService.getChartData());
    }

    @GetMapping
    public ResponseEntity<?>getStats()
    {
        return ResponseEntity.ok(statsService.getStats());
    }

}
