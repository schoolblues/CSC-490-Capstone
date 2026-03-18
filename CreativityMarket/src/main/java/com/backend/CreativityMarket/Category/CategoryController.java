package com.backend.CreativityMarket.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.backend.CreativityMarket.Admin.AdminService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final AdminService adminService; // optional, to check admin/session

    // List all categories
    @GetMapping
    public String listCategories(Model model, @SessionAttribute("adminId") Long adminId) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("admin", adminService.getCurrentAdmin(adminId));
        return "admin/categories"; // template: /templates/admin/categories.ftl
    }

    // Show form to create a new category
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form"; // template for create/edit
    }

    // Handle form submission for creating a new category
    @PostMapping("/new")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.createCategory(category.getName(), category.getDescription());
        return "redirect:/admin/categories";
    }

    // Show form to edit existing category
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategorById(id);
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    // Handle form submission for editing
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
        categoryService.updateCategory(id, category.getName(), category.getDescription());
        return "redirect:/admin/categories";
    }

    // Delete a category
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}