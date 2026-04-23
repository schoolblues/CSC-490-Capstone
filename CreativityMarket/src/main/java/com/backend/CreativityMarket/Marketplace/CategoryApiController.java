package com.backend.CreativityMarket.Marketplace;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get a category by ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // Get a category by name
    @GetMapping("/by-name")
    public Category getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name);
    }
}