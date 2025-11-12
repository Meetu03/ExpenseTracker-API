package com.expensetracker.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController 
{
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) 
    {
        this.service = service;
    }

    @GetMapping
    public List<Expense> getAllExpenses() 
    {
        return service.getAllExpenses();
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody @Valid ExpenseRequest req) 
    {
        Expense saved = service.addExpense(req);
        return ResponseEntity.created(URI.create("/api/expenses/" + saved.getId())).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id)
    {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public List<Expense> getByCategory(@PathVariable Long categoryId) 
    {
        return service.getExpensesByCategory(categoryId);
    }
}
