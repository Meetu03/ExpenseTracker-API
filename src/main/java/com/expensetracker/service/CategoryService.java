package com.expensetracker.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.expensetracker.model.Category;
import com.expensetracker.repository.CategoryRepository;

@Service
public class CategoryService 
{
    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) 
    {
        this.categoryRepo = categoryRepo;
    }

    public Category save(Category c) {
        return categoryRepo.save(c);
    }

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    public Category findById(Long id) 
    {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}
