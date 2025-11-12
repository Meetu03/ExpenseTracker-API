package com.expensetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService 
{
    private final ExpenseRepository expenseRepo;
    private final CategoryService categoryService;

    public ExpenseService(ExpenseRepository expenseRepo, CategoryService categoryService) 
    {
        this.expenseRepo = expenseRepo;
        this.categoryService = categoryService;
    }

    public List<Expense> getAllExpenses() 
    {
        return expenseRepo.findAll();
    }

    @Transactional
    public Expense addExpense(ExpenseRequest req) 
    {
        // Ensure category exists and use managed entity
        Category cat = categoryService.findById(req.getCategoryId());

        Expense e = new Expense();
        e.setTitle(req.getTitle());
        e.setAmount(req.getAmount());
        e.setDate(req.getDate());
        e.setCategory(cat);

        return expenseRepo.save(e);
    }

    public void deleteExpense(Long id) 
    {
        expenseRepo.deleteById(id);
    }

    public List<Expense> getExpensesByCategory(Long categoryId) 
    {
        return expenseRepo.findByCategoryId(categoryId);
    }
}
