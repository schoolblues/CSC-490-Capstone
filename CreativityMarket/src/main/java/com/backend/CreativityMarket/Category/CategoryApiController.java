package com.backend.CreativityMarket.Category;

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
        return categoryService.getCategorById(id);
    }

    // Get a category by name
    @GetMapping("/by-name")
    public Category getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name);
    }

    // Create a new category
    @PostMapping
    public Category createCategory(
            @RequestParam String name,
            @RequestParam String description
    ) {
        return categoryService.createCategory(name, description);
    }

    // Update an existing category
    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description
    ) {
        return categoryService.updateCategory(id, name, description);
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}